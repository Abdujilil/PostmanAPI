package com.postman.pojos.request.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request {

    private String url;
    private String method;

    public Request() {
    }

    public Request(String url, String method) {
        this.url = url;
        this.method = method;
    }
}
