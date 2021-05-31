package com.github.fabriciolfj.springclient.controller;

import com.github.fabriciolfj.springclient.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/ingredients/{id}";
    private static final String URL2 = "http://localhost:8080/ingredients";
    private static final String URL3 = "http://localhost:8080/designtaco/recent";

    @GetMapping("/1/{ingredient}")
    public Ingredient example1(@PathVariable("ingredient") String id) {
        return restTemplate.getForObject(URL, Ingredient.class, id);
    }

    @GetMapping("/2/{ingredient}")
    public Ingredient example2(@PathVariable("ingredient") String id) {
        var variables = new HashMap<String, String>();
        variables.put("id", id);
        return restTemplate.getForObject(URL, Ingredient.class, variables);
    }

    @GetMapping("/3/{ingredient}")
    public Ingredient example3(@PathVariable("ingredient") String id) {
        var variables = new HashMap<String, String>();
        variables.put("id", id);

        var url = UriComponentsBuilder.fromHttpUrl(URL).build(variables);
        return restTemplate.getForObject(url, Ingredient.class);
    }

    //retorn um responseentity
    @GetMapping("/4/{ingredient}")
    public Ingredient example4(@PathVariable("ingredient") String id) {
        var variables = new HashMap<String, String>();
        variables.put("id", id);

        var url = UriComponentsBuilder.fromHttpUrl(URL).build(variables);
        var response = restTemplate.getForEntity(url, Ingredient.class);

        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCodeValue());
        return response.getBody();
    }

    @PostMapping
    public Ingredient example5(@RequestBody Ingredient ingredient) {
        return restTemplate.postForObject(URL2, ingredient, Ingredient.class);
    }
}
