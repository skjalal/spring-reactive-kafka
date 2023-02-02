package com.example.config;

import com.example.model.Book;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.List;

@Configuration
public class KafkaConfig {

  @Bean
  public ReactiveKafkaConsumerTemplate<String, Book> bookConsumer(KafkaProperties kafkaProperties) {
    var options =
        ReceiverOptions.<String, Book>create(kafkaProperties.buildConsumerProperties())
                .subscription(List.of("book"));
    return new ReactiveKafkaConsumerTemplate<>(options);
  }

  @Bean
  public ReactiveKafkaProducerTemplate<String, Book> bookProducer(KafkaProperties kafkaProperties) {
    return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(kafkaProperties.buildProducerProperties()));
  }
}
