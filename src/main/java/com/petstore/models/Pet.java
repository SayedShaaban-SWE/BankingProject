package com.petstore.models;

import lombok.Data;

@Data
public class Pet {
    private int id;
    private String name;
    private String status;
}