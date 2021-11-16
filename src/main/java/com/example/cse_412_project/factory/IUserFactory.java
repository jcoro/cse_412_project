package com.example.cse_412_project.factory;

import com.example.cse_412_project.entities.IAppUser;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.web.forms.UserForm;

/**
 * IUserFactory.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/
public interface IUserFactory {

    AppUser createUser(UserForm userForm);

    AppUser createUser(String username, String password, String role, boolean enabled);

}
