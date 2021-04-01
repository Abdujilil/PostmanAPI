package com.postman.pojos.response.environments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Environment {

    private String id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String owner;
    private String uid;
    private Boolean isPublic;

}
