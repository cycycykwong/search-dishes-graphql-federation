package com.cycycykwong.restaurant.dataloader

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.service.RestaurantService
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.BatchLoader
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.streams.toList

@DgsDataLoader(name = "restaurant")
class RestaurantDataLoader(
    @Autowired private val restaurantService: RestaurantService,
) : BatchLoader<String, Restaurant> {
    val logger = LoggerFactory.getLogger(RestaurantDataLoader::class.java)

    fun loadRestaurants(keys: List<String>): List<Restaurant> {
        // TODO: get restaurants by keys
        logger.info("Restaurant service: Load restaurants by keys $keys")
        return keys.mapNotNull { restaurantService.restaurantById(id = it) }
    }

    override fun load(keys: MutableList<String>): CompletionStage<List<Restaurant>> {
        return CompletableFuture.supplyAsync {
            loadRestaurants(keys.stream().toList())
        }
    }
}