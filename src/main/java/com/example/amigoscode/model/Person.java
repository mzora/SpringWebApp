package com.example.amigoscode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private UUID ID;
    private String name;
/*
    public Person(@JsonProperty("id") UUID ID,
                  @JsonProperty("name") String name) {
        this.ID = ID;
        this.name = name;
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }*/
}
