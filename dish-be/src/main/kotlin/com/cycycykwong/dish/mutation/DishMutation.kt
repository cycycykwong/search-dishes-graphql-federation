package com.cycycykwong.dish.mutation

import com.cycycykwong.dish.service.DishService
import com.cycycykwong.generated.types.Dish
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.springframework.beans.factory.annotation.Autowired

@DgsComponent
class DishMutation(@Autowired val dishService: DishService){
    @DgsMutation
    fun addDish(
        @InputArgument restaurantId: String?,
        @InputArgument name: String?,
        @InputArgument price: Double?): Dish? {
        return if(restaurantId.isNullOrEmpty() || name.isNullOrEmpty() || price == null) {
            null
        } else {
            dishService.addDish(restaurantId, name, price)
        }
    }
}