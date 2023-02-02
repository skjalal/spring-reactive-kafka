package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
  private Integer id;
  private String name;
  private String version;
  private Integer price;
  private String author;
}
