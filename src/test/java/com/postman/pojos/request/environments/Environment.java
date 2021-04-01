package com.postman.pojos.request.environments;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Environment {

    private String id;
    private String name;
    private List<Value> values;

    public Environment() {
    }

    public Environment(String name) {
        this.name = name;
    }

    public Environment(String name, List<Value> values) {
        this.name = name;
        this.values = values;
    }
}
