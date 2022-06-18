package com.cycycykwong.dish.entity

import com.cycycykwong.generated.types.Dish
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "dish")
data class DishEntity(
    @Id
    val id: ObjectId = ObjectId(),
    val name: String,
    val price: Double,
)

fun List<DishEntity>.toDishes(): List<Dish> {
    return this.map { Dish(
        id = it.id.toString(),
        name = it.name,
        price = it.price
    ) }
}