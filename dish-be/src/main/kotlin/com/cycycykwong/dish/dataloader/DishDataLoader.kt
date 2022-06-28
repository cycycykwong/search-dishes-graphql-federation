package com.cycycykwong.dish.dataloader

import com.cycycykwong.dish.service.DishService
import com.cycycykwong.generated.types.Dish
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.streams.toList

@DgsDataLoader(name = "dishes")
class DishDataLoader(
    @Autowired private val dishService: DishService,
) : MappedBatchLoader<String, List<Dish>> {
    val logger = LoggerFactory.getLogger(DishDataLoader::class.java)

    override fun load(keys: MutableSet<String>): CompletionStage<MutableMap<String, List<Dish>>> {
        logger.info("Dish service: Load dishes for restaurants $keys")
        return CompletableFuture.supplyAsync {
            dishService.dishesForRestaurants(keys.stream().toList())
        }
    }
}