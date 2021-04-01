package com.postman.pojos.response.collections.allCollections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllCollections {

    private List<Collection> collections;

}
