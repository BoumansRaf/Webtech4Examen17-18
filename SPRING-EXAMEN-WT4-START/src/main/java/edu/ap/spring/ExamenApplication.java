package edu.ap.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import edu.ap.spring.controller.ExamenController;

import edu.ap.spring.redis.RedisService;

@SpringBootApplication
public class ExamenApplication {

	private RedisService service;
	private String CHANNEL = "edu:ap:redis";
	private String KEY = "edu:ap:test";
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
	}
	@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new ChannelTopic(CHANNEL));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ExamenController controller) {
		return new MessageListenerAdapter(controller, "onMessage");
	}
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ExamenApplication.class, args);
	}
	
}
