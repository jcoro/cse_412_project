package com.example.cse_412_project.entities;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface IAppUser {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    void setEnabled(boolean enabled);

    void setCredentialsNonExpired(boolean credentialsNonExpired);

    void setAccountNonLocked(boolean accountNonLocked);

    void setAccountNonExpired(boolean accountNonExpired);

    void setRoles(Set<SimpleGrantedAuthority> roles);

    Set<SimpleGrantedAuthority> getRoles();

}
