package com.postman.pojos.request.environments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewEnvironment {

    private Environment environment;

    public NewEnvironment() {
    }

    public NewEnvironment(Environment environment) {
        this.environment = environment;
    }

}
