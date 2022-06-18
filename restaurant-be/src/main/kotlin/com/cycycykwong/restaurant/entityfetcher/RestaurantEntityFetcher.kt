package com.cycycykwong.restaurant.entityfetcher

import com.cycycykwong.generated.types.Restaurant
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsEntityFetcher
import org.dataloader.DataLoader
import java.util.*
import java.util.concurrent.CompletableFuture

@DgsComponent
class RestaurantEntityFetcher {
    @DgsEntityFetcher(name = "Restaurant")
    fun university(values: Map<String, Objects>, dfe: DgsDataFetchingEnvironment): CompletableFuture<Restaurant> {
        val dataLoader: DataLoader<String, Restaurant> = dfe.getDataLoader("restaurant")
        return dataLoader.load(values["id"].toString())
    }
}