package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpringReactiveKafkaApplicationTests {

  @Test
  void contextLoads() {
    assertEquals("2.7.8", SpringBootVersion.getVersion());
  }
}
