package com.blog.security;
//package com.springboot.blog.security;
import com.blog.entity.Role;
import com.blog.exception.BlogAPIException;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
//org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")     private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    private Role authentication;
    // generate token
    public String generateToken(Authentication authentication){//authentication referance object ,this has the username and password
    String username = authentication.getName();
    Date currentDate = new Date();//it will give you cureent date
    Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);//and expiry time

    String token = Jwts.builder()//it is generate token and apply one algorithm is HS512 and jwtsecret key and this token has three parts 1.header 2.payload3.signature
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
        return token;//and it given  that token to authcontroller
}

// get username from the token
public String getUsernameFromJWT(String token){
Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
return claims.getSubject();

    }

            // validate JWT token
            public boolean validateToken(String token) throws BlogAPIException {
            try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
                    }catch (SignatureException ex){ throw new

 BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature") {

};
        } catch (MalformedJwtException ex) {throw new
                    BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) { throw new
BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) { throw new
BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) { throw new
BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
                }

                }



