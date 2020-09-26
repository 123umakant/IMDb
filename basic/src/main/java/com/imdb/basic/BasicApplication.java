package com.imdb.basic;

import com.imdb.basic.model.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class BasicApplication {


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	return new JedisConnectionFactory();
    }
	@Bean
	RedisTemplate<String, Movie> redisTemplate(){
		RedisTemplate<String,Movie> redisTemplate = new RedisTemplate<String, Movie>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}


    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

}
