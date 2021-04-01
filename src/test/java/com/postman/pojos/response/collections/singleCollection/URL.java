package com.postman.pojos.response.collections.singleCollection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class URL {

    private String raw;
    private String protocol;
    private List<String> host;
    private List<String> path;

}
