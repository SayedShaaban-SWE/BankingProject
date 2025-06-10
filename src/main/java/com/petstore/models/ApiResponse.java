package com.petstore.models;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private int id;
    private String name;
    private List<Object> photoUrls;
    private List<Object> tags;
    private String status;
}