package com.cycycykwong.restaurant.mutation

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.service.RestaurantService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class RestaurantMutation(@Autowired val restaurantService: RestaurantService) {
    @DgsMutation
    fun addRestaurant(
        @InputArgument name: String?,
        @InputArgument address: String?
    ): Restaurant? {
        return if (name.isNullOrEmpty() || address.isNullOrEmpty()) {
            null
        } else {
            restaurantService.addRestaurant(name, address)
        }
    }
}