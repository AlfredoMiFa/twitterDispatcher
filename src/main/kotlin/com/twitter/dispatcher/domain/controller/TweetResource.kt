package com.twitter.dispatcher.domain.controller

import com.twitter.dispatcher.domain.service.Tweet
import com.twitter.dispatcher.domain.service.TwitterDispatcher
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/tweets")
class TweetResource(private val dispatcher: TwitterDispatcher) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun tweets(@RequestParam("q")query:String): Flux<Tweet> {
        return dispatcher.dispatch()
                .filter({tweet: Tweet? -> tweet!!.text.contains(query,ignoreCase = true)})
    }

}