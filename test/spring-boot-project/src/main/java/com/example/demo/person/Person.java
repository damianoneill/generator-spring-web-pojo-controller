package com.example.demo.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // required for builder with NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Slf4j
public class Person {

    @NotNull
    String name;

    @Min(18)
    int age;

    @Email
    String email;
}
