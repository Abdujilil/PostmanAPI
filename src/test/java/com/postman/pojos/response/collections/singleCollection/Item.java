package com.postman.pojos.response.collections.singleCollection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

    private String name;
    private String id;
    private Request request;

}
