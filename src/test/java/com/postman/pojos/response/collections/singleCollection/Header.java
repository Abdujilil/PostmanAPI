package com.postman.pojos.response.collections.singleCollection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Header {

    private String key;
    private String value;
    private String description;

}
