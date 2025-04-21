package com.example.OnlineFoodOrdering.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        System.out.println("Roles are the following"+roles);
        Set<String> auths = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (!role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }
            auths.add(role);
        }
        
        // for(GrantedAuthority authority:authorities){
        //     auths.add("ROLE_"+authority.getAuthority());
        // }
        String jwt = Jwts.builder().setIssuedAt(new Date())
        .setExpiration((new Date(new Date().getTime()+86400000)))
        .claim("email",auth.getName())
        .claim("authorities",roles)
        .claim("roles", auths.toArray())
        .signWith(key)
        .compact();
        
        return jwt;
    }
    
    public String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        // String email = String.valueOf(claims.get("rol"));
        String email = String.valueOf(claims.get("email"));
        return email;
    }
    
    public List<String> getRolesFromJwtToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        
        List<String> roles = claims.get("authorities", List.class); 
        System.out.println("Extracted Roles: " + roles);
        
        return roles;
    }
    
    
    private String populateAuthorities(Collection <? extends GrantedAuthority> authorities){
        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }

}

// public String generateToken(Authentication auth) {
//     Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
    
//     List<String> roles = authorities.stream()
//                                     .map(GrantedAuthority::getAuthority)
//                                     .collect(Collectors.toList()); 
    

//     String jwt = Jwts.builder()
//         .setIssuedAt(new Date())
//         .setExpiration(new Date(new Date().getTime() + 86400000))
//         .claim("email", auth.getName())
//         .claim("roles", roles) 
//         .signWith(key)
//         .compact();

//     return jwt;
// }