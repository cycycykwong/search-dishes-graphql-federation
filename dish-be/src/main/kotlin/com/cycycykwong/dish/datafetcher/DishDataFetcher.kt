package com.cycycykwong.dish.datafetcher

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.dish.service.DishService
import com.cycycykwong.generated.types.Restaurant
import com.netflix.graphql.dgs.*
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class DishDataFetcher(@Autowired val dishService: DishService) {
    @DgsQuery
    fun dishes(): List<Dish> {
        return dishService.dishes()
    }

    @DgsEntityFetcher(name = "Restaurant")
    fun restaurant(values: Map<String, Object>): Restaurant {
        return Restaurant(id = values["id"] as String)
    }

    @DgsData(parentType = "Restaurant", field = "dishes")
    fun restaurantDishes(dataFetchingEnvironment: DgsDataFetchingEnvironment): List<Dish> {
        val restaurant = dataFetchingEnvironment.getSource<Restaurant>()
        return dishService.dishesByRestaurantId(restaurant.id)
    }
}