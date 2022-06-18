package com.cycycykwong.dish.service

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.dish.entity.DishEntity
import com.cycycykwong.dish.entity.toDish
import com.cycycykwong.dish.entity.toDishes
import com.cycycykwong.dish.repository.DishRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface DishService {
    fun dishes(): List<Dish>
    fun addDish(restaurantId: String, name: String, price: Double): Dish
}

@Service
class SimpleDishService(@Autowired val dishRepository: DishRepository): DishService {
    override fun dishes(): List<Dish> {
        return dishRepository.findAll().toDishes()
    }

    override fun addDish(restaurantId: String, name: String, price: Double): Dish {
        val newDish = DishEntity(
            restaurantId = restaurantId,
            name = name,
            price = price,
        )
        return dishRepository.save(newDish).toDish()
    }
}