package pl.kielce.tu.travel_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kielce.tu.travel_agency.model.dto.CredentialsDto;
import pl.kielce.tu.travel_agency.model.dto.JWTToken;
import pl.kielce.tu.travel_agency.model.dto.PersonDto;
import pl.kielce.tu.travel_agency.model.dto.RegistrationDto;
import pl.kielce.tu.travel_agency.security.SecurityUtils;
import pl.kielce.tu.travel_agency.security.jwt.JWTTokenFilter;
import pl.kielce.tu.travel_agency.security.jwt.JWTTokenProvider;
import pl.kielce.tu.travel_agency.services.PersonService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final SecurityUtils utils;

    private final AuthenticationManager authenticationManager;

    private final PersonService personService;

    private final JWTTokenProvider tokenProvider;

    @Autowired
    public AuthController(SecurityUtils utils,
                          AuthenticationManager authenticationManager,
                          PersonService personService,
                          JWTTokenProvider tokenProvider) {
        this.utils = utils;
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate(@RequestBody CredentialsDto credentialsDto) throws Exception {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentialsDto.getEmail(), credentialsDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, credentialsDto.getRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTTokenFilter.AUTHORIZATION_HEADER, "Bearer "+jwt);
        PersonDto person = new PersonDto(utils.getCurrentPerson());
        return new ResponseEntity<>(new JWTToken(jwt, person), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws Exception{

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDto registrationDto) throws Exception{
        personService.register(registrationDto);

        return ResponseEntity.ok().build();
    }
}
