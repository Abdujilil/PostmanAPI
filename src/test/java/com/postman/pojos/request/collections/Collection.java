package com.postman.pojos.request.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Collection {

    private Info info;
    private List<Item> item;

    public Collection() {
    }

    public Collection(Info info, List<Item> item) {
        this.info = info;
        this.item = item;
    }

}
