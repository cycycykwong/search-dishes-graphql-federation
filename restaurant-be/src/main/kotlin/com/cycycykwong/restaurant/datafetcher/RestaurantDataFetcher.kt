package com.cycycykwong.restaurant.datafetcher

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.service.RestaurantService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import org.dataloader.DataLoader
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import java.util.concurrent.CompletableFuture

@DgsComponent
class RestaurantDataFetcher(@Autowired val restaurantService: RestaurantService) {
    val logger = LoggerFactory.getLogger(RestaurantDataFetcher::class.java)

    @DgsQuery
    fun restaurants(): List<Restaurant> {
        logger.info("Restaurant service: Get all restaurants")
        return restaurantService.restaurants()
    }

    @DgsEntityFetcher(name = "Restaurant")
    fun restaurant(
        values: Map<String, Objects>,
        dfe: DgsDataFetchingEnvironment
    ): CompletableFuture<Restaurant> {
        val dataLoader: DataLoader<String, Restaurant> = dfe.getDataLoader("restaurant")
        return dataLoader.load(values["id"].toString())
    }
}