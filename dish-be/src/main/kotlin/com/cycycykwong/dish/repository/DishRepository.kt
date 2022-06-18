package com.cycycykwong.dish.repository

import com.cycycykwong.dish.entity.DishEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DishRepository : MongoRepository<DishEntity, String>