package com.itest.baseapplication.Auth;

import com.itest.baseapplication.dto.ProfileDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


@Component
public class AuthUtil {

    @Value("${YASAKANI_NO_MAGATAMA}")
    private String YASAKANI_NO_MAGATAMA;





    public  String createJWT( String id, String issuer, String subject, Map <String,Object> claims) {
        long ttlMillis=28800000;
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(YASAKANI_NO_MAGATAMA);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setClaims(claims)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public String encodeRole ( String role){
        byte[] roleByte = Base64.getEncoder().encode(role.getBytes(StandardCharsets.UTF_8));

        String roleByteEncoded = new String(roleByte);
        String salt = RandomSalt.getSalt();
        return salt+roleByteEncoded;
    }

    public String decodeRole(String encodedString){

        String salt = RandomSalt.getSalt();
        String splitStr = encodedString.substring(salt.length());
        byte[] rolebye = Base64.getDecoder().decode(splitStr);
        return new String(rolebye);
    }

    private Jws <Claims> getDecodedToken ( String token) throws JwtAuthenticationException {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(YASAKANI_NO_MAGATAMA);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Jws<Claims> claimsJws= null;
                try{
                claimsJws = Jwts.parser().setSigningKey(signingKey)
                        .parseClaimsJws(token);}
                catch (MalformedJwtException e){
                    throw new JwtAuthenticationException("Bad Token");
                }
                return claimsJws;
    }

    public Claims getClaimFromDecodedToken ( String token ) throws JwtAuthenticationException {
        return getDecodedToken(token).getBody();
    }

    public Object getValuesFromClaims(Claims claims,String key){
        return claims.get(key);
    }

    public String getUserNameFromToken(Claims token){
        return token.get("username",String.class);
    }

    public boolean isExpired(Claims claims){
         return claims.getExpiration().before(new Date());
    }

    public Optional<ProfileDTO> verify(String jwtToken) throws  JwtAuthenticationException {

        Claims jwtClaim           = null;
        String userName           = null;

        try{

            jwtClaim = getClaimFromDecodedToken(jwtToken);
            if(isExpired(jwtClaim))
                throw new IOException("Expired token");

            userName = getUserNameFromToken(jwtClaim);


        }catch (IllegalArgumentException | IOException e){
            throw new JwtAuthenticationException("Unable to get JWT Token, " +
                    "There maybe problem in the token");
        }
        String role =(String) getValuesFromClaims(jwtClaim,"token_r");
        role = decodeRole(role);


        if( !(role.equals("developer") || role.equals("admin") || role.equals("tester")) ){
            throw new JwtAuthenticationException("Token looks tampered");
        }

        String fullName=jwtClaim.get("fullName",String.class);
        String userId=jwtClaim.get("userId",String.class);



        return Optional.of(new ProfileDTO(userId,userName,fullName,role));
    }


}
