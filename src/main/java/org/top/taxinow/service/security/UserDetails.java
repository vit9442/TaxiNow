package org.top.taxinow.service.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.User;

import java.util.Collection;
import java.util.Collections;

@Service
@Getter
@Setter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User dispatchers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return Collections
               .<GrantedAuthority>singletonList(new SimpleGrantedAuthority("ROLE_" + dispatchers.getRole()));
    }

    @Override
    public String getPassword() {
        return dispatchers.getPassword();
    }

    @Override
    public String getUsername() {
        return dispatchers.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
