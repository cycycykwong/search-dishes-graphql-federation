package com.cycycykwong.dish.datafetcher

import com.cycycykwong.generated.types.Dish
import com.cycycykwong.dish.service.DishService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class DishDataFetcher(@Autowired val dishService: DishService) {
    @DgsQuery
    fun dishes(): List<Dish> {
        return dishService.dishes()
    }
}