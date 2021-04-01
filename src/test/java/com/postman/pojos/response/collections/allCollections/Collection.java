package com.postman.pojos.response.collections.allCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class Collection {

    private String id;
    private String name;
    private String owner;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String uid;
    private boolean isPublic;

}
