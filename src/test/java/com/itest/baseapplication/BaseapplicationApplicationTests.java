package com.itest.baseapplication;

import com.itest.baseapplication.Auth.AuthUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseapplicationApplicationTests {
	@LocalServerPort
	private int port;

//	@Test
	void contextLoads() {
	}

	@Test
	void checkEncode() throws Exception{
		AuthUtil authUtil = new AuthUtil();

		HashMap <String,Object> claimsObj = new HashMap <>();
		claimsObj.put("username","mehul");
		claimsObj.put("token_r","12345678");
		claimsObj.put("fullName","mehul aswani");

		String token = authUtil.createJWT("mehul__pero","iTest-Admin","special-token",claimsObj);


		Claims obj = authUtil.getClaimFromDecodedToken(token);
		Assertions.assertEquals("mehul",obj.get("username",String.class));
	}

}
