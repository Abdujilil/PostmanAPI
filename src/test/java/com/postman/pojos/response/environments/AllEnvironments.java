package com.postman.pojos.response.environments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AllEnvironments {

    private List<Environment> environments;

}
