package com.postman.pojos.request.environments;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Value {

    private String key;
    private String value;
    private String type;
    private boolean enabled;
    private boolean hovered;

    public Value() {
    }

    public Value(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
