package com.example.demo.SpringSecurityDetail;


import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.List;

@Component
public class JwtVerify extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    Token token_class;
    @Autowired
    UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header=request.getHeader("Authorization");
        if(header==null || !(header.startsWith("Bearer"))){
         filterChain.doFilter(request,response);
         return;
        }
         String token = header.split ("Bearer ")[1];
        String username;
        try {
            username = token_class.getUsernameFromToken(token);
        }
        catch (ExpiredJwtException ex) {
            throw new BadCredentialsException("TOKEN_EXPIRED", ex);
        }
        catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_TOKEN", ex);
        }


        if(token!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user1=userRepo.findByEmail(username).orElseThrow();
            if(user1.getIsactive()==false){
                throw new LockedException("User is Blocked");
            }
            UserDetailImpl user2=new UserDetailImpl(user1);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    =new UsernamePasswordAuthenticationToken(user2,null,user2.getAuthorities());

           // SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("Hello complete Execution");
            System.out.println(user1.getRole());
        }

        filterChain.doFilter(request,response);



    }
}
