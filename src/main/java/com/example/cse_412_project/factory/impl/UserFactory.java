package com.example.cse_412_project.factory.impl;

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
    public AppUser createUser(UserForm userForm) {
        AppUser user = new AppUser();
        user.setPassword(userForm.getPassword());
        user.setUsername(userForm.getUsername());

        return user;
    }

    @Override
    public AppUser createUser(String username, String password, String role, boolean enabled) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled(enabled);
        return user;
    }
}
