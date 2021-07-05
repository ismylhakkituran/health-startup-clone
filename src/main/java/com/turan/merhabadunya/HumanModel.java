package com.turan.merhabadunya;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class HumanModel {

    private String name;
    private int age;
    private String surname;


    public HumanModel(String name, int age, String surname) {
        this.name = name;
        this.age = age;
        this.surname = surname;
    }
}
