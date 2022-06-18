package com.cycycykwong.restaurant.service

import com.cycycykwong.generated.types.Restaurant
import com.cycycykwong.restaurant.entity.RestaurantEntity
import com.cycycykwong.restaurant.entity.toRestaurant
import com.cycycykwong.restaurant.entity.toRestaurants
import com.cycycykwong.restaurant.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface RestaurantService {
    fun restaurants(): List<Restaurant>
    fun restaurantById(id: String): Restaurant?
    fun addRestaurant(name: String, address: String): Restaurant
}

@Service
class SimpleRestaurantService(@Autowired val restaurantRepository: RestaurantRepository) : RestaurantService {
    override fun restaurants(): List<Restaurant> {
        return restaurantRepository.findAll().toRestaurants()
    }

    override fun restaurantById(id: String): Restaurant? {
        return restaurantRepository.findByIdOrNull(id)?.toRestaurant()
    }

    override fun addRestaurant(name: String, address: String): Restaurant {
        val newRestaurant = RestaurantEntity(
            name = name,
            address = address
        )
        return restaurantRepository.save(newRestaurant).toRestaurant()
    }
}