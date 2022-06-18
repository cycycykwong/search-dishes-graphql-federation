package com.cycycykwong.dish.entity

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.generated.types.Restaurant
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "dish")
data class DishEntity(
    @Id
    val id: ObjectId = ObjectId(),
    val restaurantId: String,
    val name: String,
    val price: Double,
)

fun List<DishEntity>.toDishes(): List<Dish> {
    return this.map { it.toDish() }
}

fun DishEntity.toDish(): Dish {
    return Dish(
        id = this.id.toString(),
        restaurant = Restaurant(id = this.restaurantId),
        name = this.name,
        price = this.price
    )
}