package com.postman.pojos.response.collections.singleCollection;

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

}
