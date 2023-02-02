package com.example.service;

import com.example.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsumerService implements CommandLineRunner {

  private final ReactiveKafkaConsumerTemplate<String, Book> bookConsumer;

  @Override
  public void run(String... args) {
    bookConsumer.receiveAutoAck().doOnNext(this::consumer).doOnError(this::catchError).subscribe();
  }

  private void consumer(ConsumerRecord<String, Book> data) {
    log.info("Received: Key: {} - Value: {}", data.key(), data.value());
  }

  private void catchError(Throwable ex) {
    log.error("Failed to load consumer data", ex);
  }
}
