package com.dexlock.book.Book.controller;

import com.dexlock.book.Book.JResponse;
import com.dexlock.book.Book.config.JwtTokenUtil;
import com.dexlock.book.Book.model.User;
import com.dexlock.book.Book.repository.UserRepository;
import com.dexlock.book.Book.services.JUserDetailsService;
import com.dexlock.book.Book.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtoken;

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final  String token = jwtoken.generateToken(userDetails);
        return ResponseEntity.ok(new JResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userRepository.save(user));
    }
}
