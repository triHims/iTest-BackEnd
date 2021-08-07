package com.itest.baseapplication.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class LoginStatusDTO implements Serializable {
   private static UUID serialVersionUID = UUID.randomUUID();
   private final boolean loginStatus;
   private final String secretKey;
}
