package com.cycycykwong.restaurant.datafetcher

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.service.RestaurantService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class RestaurantDataFetcher(@Autowired val restaurantService: RestaurantService) {
    @DgsQuery
    fun restaurants(): List<Restaurant> {
        return restaurantService.restaurants()
    }
}