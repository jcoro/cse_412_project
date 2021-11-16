package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.DTO.AuthenticationResponse;
import com.example.cse_412_project.DTO.LoginRequest;
import com.example.cse_412_project.DTO.RegisterRequest;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.exceptions.UserAlreadyExistsException;
import com.example.cse_412_project.exceptions.UserDoesNotExistException;
import com.example.cse_412_project.factory.IUserFactory;
import com.example.cse_412_project.factory.impl.LoadInitialData;
import com.example.cse_412_project.repositories.UserRepository;
import com.example.cse_412_project.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Transactional
@Service
//@PropertySource("classpath:/user.properties")
public class UserService {

    private final Environment environment;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final IUserFactory userFactory;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(final Environment env,
                       final UserRepository userRepository,
                       final BCryptPasswordEncoder bCryptPasswordEncoder,
                       final IUserFactory userFactory,
                       final AuthenticationManager authenticationManager,
                       final JwtProvider jwtProvider){
        this.environment = env;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public AppUser findUserByUsername(String arg0) throws UsernameNotFoundException {
        Optional<AppUser> foundUser = userRepository.findById(arg0);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }

        String userDetails = environment.getProperty(arg0);
        if (userDetails != null) {
            String[] details = userDetails.split(",");
            return userFactory.createUser(arg0, details[0], details[1], (details[2].equals("enabled")));
        }

        throw new UsernameNotFoundException(String.format("No user with username %s found.", arg0));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponse.builder().authenticationToken(token)
                .expiresAt(Instant.now().plusMillis(System.currentTimeMillis() + 600000).toEpochMilli())
                .username(loginRequest.getUsername())
                .build();
    }

    public boolean signup(RegisterRequest registerRequest) {
        Optional<AppUser> existingUser = userRepository.findById(registerRequest.getUsername());
        if (existingUser.isPresent()) {
            return false;
        }
        AppUser user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());

        // we can set this to false if we use token => after validating token, set it to true
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    public void changePassword(AppUser user, String password) throws UserDoesNotExistException {
        Optional<AppUser> existingUser = userRepository.findById(user.getUsername());
        if (!existingUser.isPresent()) {
            throw new UserDoesNotExistException("User " + user.getUsername() + " does not exist.");
        }
        user = existingUser.get();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save((AppUser)user);
    }

    public AppUser findByUsername(String username) {
        Optional<AppUser> foundUser = userRepository.findById(username);
        return foundUser.orElse(null);
    }

    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public List<AppUser> findAll() {
        Iterable<AppUser> users = userRepository.findAll();
        List<AppUser> results = new ArrayList<>();
        users.iterator().forEachRemaining(results::add);
        return results;
    }

    public void disableUser(String username, String initiator) {
        AppUser user = findByUsername(username);
        user.setEnabled(false);
        userRepository.save(user);
    }
}
