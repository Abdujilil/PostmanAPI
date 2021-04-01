package com.postman.pojos.request.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {

    private String name;
    private Request request;

    public Item() {
    }

    public Item(String name, Request request) {
        this.name = name;
        this.request = request;
    }
}
