package com.cycycykwong.dish.datafetcher

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.dish.service.DishService
import com.cycycykwong.generated.types.Restaurant
import com.netflix.graphql.dgs.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture

@DgsComponent
class DishDataFetcher(@Autowired val dishService: DishService) {
    val logger = LoggerFactory.getLogger(DishDataFetcher::class.java)

    @DgsQuery
    fun dishes(): List<Dish> {
        logger.info("Get all dishes")
        return dishService.dishes()
    }

    @DgsEntityFetcher(name = "Restaurant")
    fun restaurant(values: Map<String, Object>): Restaurant {
        return Restaurant(id = values["id"] as String)
    }

    @DgsData(parentType = "Restaurant", field = "dishes")
    fun restaurantDishes(dataFetchingEnvironment: DgsDataFetchingEnvironment): CompletableFuture<List<Dish>> {
        val restaurant = dataFetchingEnvironment.getSource<Restaurant>()
        val dataLoader = dataFetchingEnvironment.getDataLoader<String, List<Dish>>("dishes")

        return dataLoader.load(restaurant.id)
    }
}