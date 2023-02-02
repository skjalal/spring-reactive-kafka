package com.example.service;

import com.example.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProducerService {

  private final ReactiveKafkaProducerTemplate<String, Book> bookProducer;

  public Mono<Book> send(Book book) {
    log.info("Sending book: {}", book.getName());
    return bookProducer.send("book", UUID.randomUUID().toString(), book).map(d -> callback(d, book));
  }

  private Book callback(SenderResult<Void> result, Book book) {
    log.info("Record sent...{}", result.recordMetadata().offset());
    return book;
  }
}
