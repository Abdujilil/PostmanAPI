package com.postman.pojos.request.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewCollection {

    private Collection collection;

    public NewCollection() {
    }

    public NewCollection(Collection collection) {
        this.collection = collection;
    }
}
