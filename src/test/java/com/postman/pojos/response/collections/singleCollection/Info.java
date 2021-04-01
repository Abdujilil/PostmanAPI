package com.postman.pojos.response.collections.singleCollection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Info {

    private String _postman_id;
    private String name;
    private String description;
    private String schema;

}
