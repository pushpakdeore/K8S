package com.example.user.service.service;

import com.example.user.service.dto.LoginDTO;
import com.example.user.service.dto.UserDTO;
import com.example.user.service.model.Users;
import com.example.user.service.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           JWTService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void registerUser(UserDTO userDTO, String token) {
        logger.info("Registering user with email: {}", userDTO.getEmail());
        Users users = mapUserDTOToUser(userDTO);
        users.setToken(token);
        users.setConfirmed(true); // For now set to true; in real app, this would be part of a flow
        userRepository.save(users);
        logger.debug("User saved to database: {}", users.getEmail());
    }

    public String verify(LoginDTO users) {
        logger.info("Attempting to authenticate user: {}", users.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));

        Optional<Users> user = userRepository.findByEmail(users.getEmail());

        if (user.isPresent() && user.get().isConfirmed()) {
            if (authentication.isAuthenticated()) {
                logger.info("Authentication successful for user: {}", users.getEmail());
                String token = jwtService.generateToken(users.getEmail());
                logger.debug("JWT token generated for user: {}", users.getEmail());
                return token;
            } else {
//                logger.warn("Authentication failed for user: {}", users.getEmail());
                logger.warn("[ALERT_403] Authentication failed for user: {} - Status: 403", users.getEmail());

            }
        } else {
            logger.warn("User not found or email not confirmed: {}", users.getEmail());
        }
        return "Email is not confirmed";
    }

    public Users mapUserDTOToUser(UserDTO userDTO) {
        logger.debug("Mapping UserDTO to Users entity for email: {}", userDTO.getEmail());
        Users users = new Users();
        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        users.setDob(userDTO.getDob());
        users.setGender(userDTO.getGender());
        users.setEmail(userDTO.getEmail());
        users.setPassword(encoder.encode(userDTO.getPassword()));
        return users;
    }

    public List<Users> getAllUsers() {
        logger.info("Fetching all users from database");
        return userRepository.findAll();
    }

    public String confirmEmail(String token) {
        logger.info("Confirming email with token: {}", token);
        Optional<Users> byToken = userRepository.findByToken(token);
        if (byToken.isPresent()) {
            Users users = byToken.get();
            users.setConfirmed(true);
            userRepository.save(users);
            logger.info("Email confirmed for user: {}", users.getEmail());
            return "Email confirmed successfully!";
        }
        logger.warn("Invalid or expired token used for confirmation");
        return "Invalid or expired confirmation token.";
    }
}



































//@Service
//public class UserServiceImpl {
//
//    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final JWTService jwtService;
//
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//    public UserServiceImpl(UserRepository userRepository,
//                           AuthenticationManager authenticationManager,
//                           JWTService jwtService) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//    }
//
//    public void registerUser(UserDTO userDTO, String token) {
//        Users users = mapUserDTOToUser(userDTO);
//        users.setToken(token);
////        users.setConfirmed(false);
//        users.setConfirmed(true);
//        userRepository.save(users);
//    }
//
//    public String verify(LoginDTO users) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword()));
//        Optional<Users> user = userRepository.findByEmail(users.getEmail());
//        if (user.isPresent() && user.get().isConfirmed()) {
//            if (authentication.isAuthenticated()) {
//                return jwtService.generateToken(users.getEmail());
//            }
//        }
//        return "Email is not confirmed";
//    }
//
//    public Users mapUserDTOToUser(UserDTO userDTO) {
//        Users users = new Users();
//        users.setFirstName(userDTO.getFirstName());
//        users.setLastName(userDTO.getLastName());
//        users.setDob(userDTO.getDob());
//        users.setGender(userDTO.getGender());
//        users.setEmail(userDTO.getEmail());
//        users.setPassword(encoder.encode(userDTO.getPassword()));
//        return users;
//    }
//
//    public List<Users> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public String confirmEmail(String token) {
//        Optional<Users> byToken = userRepository.findByToken(token);
//        if (byToken.isPresent()) {
//            Users users = byToken.get();
//            users.setConfirmed(true);
//            userRepository.save(users);
//            return "Email confirmed successfully!";
//        }
//        return "Invalid or expired confirmation token.";
//    }
//}
//
