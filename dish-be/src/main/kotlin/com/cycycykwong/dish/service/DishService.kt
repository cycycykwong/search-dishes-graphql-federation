package com.cycycykwong.dish.service

import com.cycycykwong.dish.entity.DishEntity
import com.cycycykwong.dish.entity.toDish
import com.cycycykwong.dish.entity.toDishes
import com.cycycykwong.dish.repository.DishRepository
import com.cycycykwong.generated.types.Dish
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface DishService {
    fun dishes(): List<Dish>
    fun dishById(id: String): Dish?
    fun dishesForRestaurants(restaurantIds: List<String>): MutableMap<String, List<Dish>>
    fun dishesByRestaurantId(restaurantId: String): List<Dish>
    fun addDish(restaurantId: String, name: String, price: Double): Dish
}

@Service
class SimpleDishService(@Autowired val dishRepository: DishRepository) : DishService {
    val logger = LoggerFactory.getLogger(SimpleDishService::class.java)

    override fun dishes(): List<Dish> {
        return dishRepository.findAll().toDishes()
    }

    override fun dishById(id: String): Dish? {
        return dishRepository.findByIdOrNull(id)?.toDish()
    }

    override fun dishesForRestaurants(restaurantIds: List<String>): MutableMap<String, List<Dish>> {
        return dishes().filter { restaurantIds.contains(it.restaurant.id) }
            .groupBy { it.restaurant.id }
            .toMutableMap()
    }

    override fun dishesByRestaurantId(restaurantId: String): List<Dish> {
        return dishRepository.findByRestaurantId(restaurantId).toDishes()
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