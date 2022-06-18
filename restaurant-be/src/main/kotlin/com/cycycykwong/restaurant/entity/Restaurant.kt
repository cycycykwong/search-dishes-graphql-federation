package com.cycycykwong.restaurant.entity

import com.cycycykwong.generated.types.Restaurant
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "restaurant")
data class RestaurantEntity(
    @Id
    val id: ObjectId = ObjectId(),
    val name: String,
    val address: String,
)

fun List<RestaurantEntity>.toRestaurants(): List<Restaurant> {
    return this.map { Restaurant(
        id = it.id.toString(),
        name = it.name,
        address = it.address
    ) }
}