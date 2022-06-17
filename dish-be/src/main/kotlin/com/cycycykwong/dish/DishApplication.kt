package com.cycycykwong.dish

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DishApplication

fun main(args: Array<String>) {
	runApplication<DishApplication>(*args)
}
