package com.example.demo.SpringSecurityDetail;

import com.example.demo.Entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailImpl implements UserDetails {

    private final User user;
// Getter and Setter created by user for safety Details
    public User getUser() {
        return user;
    }

    public UserDetailImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }//account expiration handle

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsactive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }//when pass expire

    @Override
    public boolean isEnabled() {
        return user.getIsverified();
    }
}
