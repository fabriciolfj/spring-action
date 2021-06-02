package com.github.fabriciolfj.rabbitclient.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TacoDTO {

    private Long id;
    private String placedAt;
    private String deliveryName;
}
