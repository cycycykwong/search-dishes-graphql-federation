package com.cycycykwong.restaurant.repository

import com.cycycykwong.restaurant.entity.RestaurantEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : MongoRepository<RestaurantEntity, String>