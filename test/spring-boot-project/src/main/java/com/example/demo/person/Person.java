package com.example.demo.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // required for builder with NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class Person {
    String name;
    int age;
    String email;
}
