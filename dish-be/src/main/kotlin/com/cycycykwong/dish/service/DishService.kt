package com.cycycykwong.dish.service

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.dish.entity.DishEntity
import com.cycycykwong.dish.entity.toDishes
import org.springframework.stereotype.Service

interface DishService {
    fun dishes(): List<Dish>
}

@Service
class SimpleDishService: DishService {
    override fun dishes(): List<Dish> {
        return listOf(
            DishEntity(name = "Dish A", price = 30.0)
        ).toDishes()
    }
}