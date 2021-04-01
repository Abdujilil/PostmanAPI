package com.postman.pojos.request.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Info {

    private String name;
    private String schema;

    public Info() {
    }

    public Info(String name, String schema) {
        this.name = name;
        this.schema = schema;
    }
}
