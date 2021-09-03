package com.example.cse_412_project.service;

import com.example.cse_412_project.entities.IAppUser;
import com.example.cse_412_project.exceptions.UserAlreadyExistsException;
import com.example.cse_412_project.exceptions.UserDoesNotExistException;

import java.util.List;

/**
 * IUserService.java - DESCRIPTION
 * Author: John Coronite
 * Date: 9/3/21
 **/


public interface IUserService {
    void create(IAppUser user) throws UserAlreadyExistsException;

    IAppUser findByUsername(String username);

    List<IAppUser> findAll();

    void approveAccount(String username, String approver);

    void addRole(String username, String initiator, String role);

    void removeRole(String username, String initiator, String role);

    void disableUser(String username, String initiator);

    IAppUser findByEmail(String email);

    void changePassword(IAppUser user, String password) throws UserDoesNotExistException;

}

