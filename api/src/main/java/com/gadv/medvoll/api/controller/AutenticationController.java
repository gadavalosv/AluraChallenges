package com.gadv.medvoll.api.controller;

import com.gadv.medvoll.api.domain.model.user.User;
import com.gadv.medvoll.api.domain.model.user.UserAutenticationData;
import com.gadv.medvoll.api.infrastructure.security.JWTTokenData;
import com.gadv.medvoll.api.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticateUser(@RequestBody @Valid UserAutenticationData userAutenticationData){
        Authentication authToken = new UsernamePasswordAuthenticationToken(userAutenticationData.login(), userAutenticationData.password());
        Authentication authenticatedUser = authenticationManager.authenticate(authToken);
        String tokenJWT = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }
}
