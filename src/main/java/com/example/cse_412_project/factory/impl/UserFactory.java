package com.example.cse_412_project.factory.impl;

import com.example.cse_412_project.entities.IAppUser;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.factory.IUserFactory;
import com.example.web.forms.UserForm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * UserFactory.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
@Component
public class UserFactory implements IUserFactory {

    @Override
    public IAppUser createUser(UserForm userForm) {
        IAppUser user = new AppUser();
        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(userForm.getPassword());
        user.setUsername(userForm.getUsername());

        return user;
    }

    @Override
    public IAppUser createUser(String username, String password, String role, boolean enabled) {
        IAppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role));
        user.setRoles(roles);
        return user;
    }
}
