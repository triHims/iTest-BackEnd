package com.itest.baseapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDTO {

    private String userId;
    private String username;
    private String name;
    private String role;
}
