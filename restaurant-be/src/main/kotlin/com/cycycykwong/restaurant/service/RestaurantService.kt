package com.cycycykwong.restaurant.service

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.entity.RestaurantEntity
import com.cycycykwong.restaurant.entity.toRestaurants
import org.springframework.stereotype.Service

interface RestaurantService {
    fun restaurants(): List<Restaurant>
}

@Service
class SimpleRestaurantService: RestaurantService {
    override fun restaurants(): List<Restaurant> {
        return listOf(
            RestaurantEntity(name = "Restaurant A", address = "address A")
        ).toRestaurants()
    }
}