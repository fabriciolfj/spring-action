package com.github.fabriciolfj.springclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Taco {

    private Long id;
    private String name;
    private Set<String> ingredientIds = new HashSet<>();
}