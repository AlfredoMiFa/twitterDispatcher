package com.twitter.dispatcher.infra.rabbit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.rabbitmq.Receiver
import reactor.rabbitmq.ReceiverOptions
import org.springframework.http.client.reactive.ReactorResourceFactory
import reactor.rabbitmq.RabbitFlux

@Configuration
class RabbitMQConfiguration(private @Value("\${spring.rabbitmq.host}") val host:String,
                            private @Value("\${spring.rabbitmq.port}") val port:Int,
                            private @Value("\${spring.rabbitmq.username}") val username:String,
                            private @Value("\${spring.rabbitmq.password}") val password:String) {

    @Bean
    fun mapper():ObjectMapper = ObjectMapper().registerModule(KotlinModule())
	
	@Bean
	fun connectionFactory():ConnectionFactory{
		val connectionFactory = ConnectionFactory()
		connectionFactory.username=this.username
		connectionFactory.password=this.password
		connectionFactory.host=this.host
		connectionFactory.port=this.port
		connectionFactory.useNio()
		return connectionFactory
	}

	@Bean
	fun receiver(connectionFactory: ConnectionFactory):Receiver{
		val options = ReceiverOptions()
		options.connectionFactory(connectionFactory)
		return RabbitFlux.createReceiver(options)
	}

}