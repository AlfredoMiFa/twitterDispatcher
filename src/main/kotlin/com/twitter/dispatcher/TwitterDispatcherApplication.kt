package com.twitter.dispatcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TwitterDispatcherApplication

fun main(args: Array<String>) {
	runApplication<TwitterDispatcherApplication>(*args)
}
