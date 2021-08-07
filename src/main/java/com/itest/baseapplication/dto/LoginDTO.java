package com.itest.baseapplication.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;


@Getter
public class LoginDTO {

    private static final UUID serialVersionUID = UUID.randomUUID();

    @NotNull
    String username;

    @NotNull
    String password;
}
