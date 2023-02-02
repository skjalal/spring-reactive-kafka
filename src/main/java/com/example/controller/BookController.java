package com.example.controller;

import com.example.model.Book;
import com.example.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BookController {

  private final ProducerService producerService;

  @PostMapping("/book")
  public Mono<Book> publish(@RequestBody Book book) {
    return producerService.send(book);
  }
}
