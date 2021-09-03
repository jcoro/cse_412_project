package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.IAppUser;
import com.example.cse_412_project.entities.Role;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.exceptions.UserAlreadyExistsException;
import com.example.cse_412_project.exceptions.UserDoesNotExistException;
import com.example.cse_412_project.factory.IUserFactory;
import com.example.cse_412_project.repositories.UserRepository;
import com.example.cse_412_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@PropertySource("classpath:/user.properties")
public class UserService implements UserDetailsService, IUserService {

    private final Environment environment;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final IUserFactory userFactory;

    @Autowired
    public UserService(Environment env,
                       UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       IUserFactory userFactory){
        this.environment = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        Optional<AppUser> foundUser = userRepository.findById(arg0);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }

        String userDetails = environment.getProperty(arg0);
        if (userDetails != null) {
            String[] details = userDetails.split(",");
            return (UserDetails) userFactory.createUser(arg0, details[0], details[1], (details[2].equals("enabled")));
        }

        throw new UsernameNotFoundException(String.format("No user with username %s found.", arg0));
    }

    @Override
    public void create(IAppUser user) throws UserAlreadyExistsException {
        Optional<AppUser> existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("The user already exists.");
        }
        user.setEnabled(false);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save((AppUser)user);
    }

    @Override
    public void changePassword(IAppUser user, String password) throws UserDoesNotExistException {
        Optional<AppUser> existingUser = userRepository.findById(user.getUsername());
        if (!existingUser.isPresent()) {
            throw new UserDoesNotExistException("User " + user.getUsername() + " does not exist.");
        }
        user = existingUser.get();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save((AppUser)user);
    }

    @Override
    public IAppUser findByUsername(String username) {
        Optional<AppUser> foundUser = userRepository.findById(username);
        return foundUser.orElse(null);
    }

    @Override
    public IAppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<IAppUser> findAll() {
        Iterable<AppUser> users = userRepository.findAll();
        List<IAppUser> results = new ArrayList<>();
        users.iterator().forEachRemaining(results::add);
        return results;
    }

    @Override
    public void approveAccount(String username, String approver) {
        IAppUser user = findByUsername(username);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        if (user.getNotes() == null) {
            user.setNotes("");
        }
        user.setNotes(user.getNotes() + String.format("Approved by %s. ", approver));
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(new SimpleGrantedAuthority(Role.USER));
        userRepository.save((AppUser)user);
    }

    @Override
    public void disableUser(String username, String initiator) {
        IAppUser user = findByUsername(username);
        user.setEnabled(false);
        user.setNotes(user.getNotes() + String.format("Disabled by %s. ", initiator));
        userRepository.save((AppUser)user);
    }

    @Override
    public void addRole(String username, String initiator, String role) {
        IAppUser user = findByUsername(username);
        user.setNotes(user.getNotes() + String.format("User %s added role %s. ", initiator, role));
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(new SimpleGrantedAuthority(role));
        userRepository.save((AppUser)user);
    }

    @Override
    public void removeRole(String username, String initiator, String role) {
        IAppUser user = findByUsername(username);
        user.setNotes(user.getNotes() + String.format("User %s removed role %s. ", initiator, role));
        if (user.getRoles() == null) {
            return;
        }
        SimpleGrantedAuthority roleToBeRemoved = null;
        for (SimpleGrantedAuthority authority : user.getRoles()) {
            if (authority.getAuthority().equals(role)) {
                roleToBeRemoved = authority;
                break;
            }
        }

        if (roleToBeRemoved != null) {
            user.getRoles().remove(roleToBeRemoved);
            userRepository.save((AppUser)user);
        }
    }
}
