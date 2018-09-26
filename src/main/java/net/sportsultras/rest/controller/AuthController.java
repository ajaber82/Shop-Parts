package net.sportsultras.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sportsultras.rest.dto.JwtAuthenticationResponse;
import net.sportsultras.rest.dto.RestMessage;
import net.sportsultras.rest.dto.UserSignInRest;
import net.sportsultras.rest.dto.UserSignUpRest;
import net.sportsultras.rest.model.User;
import net.sportsultras.rest.model.UserRoles;
import net.sportsultras.rest.repo.UserRepo;
import net.sportsultras.rest.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired 
	private AuthenticationManager authenticationManager ; 
	@Autowired 
	private UserRepo userRepo ; 
	@Autowired 
	private JwtTokenProvider jwtTokenProvider; 
	@Autowired
	private PasswordEncoder passwordEncoder ; 
	@Autowired
	private MessageSource messageSource ; 
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserSignInRest userSignInRequest){
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userSignInRequest.getUserEmail(),
                		userSignInRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserSignUpRest userSignUpRequest, @RequestHeader("Accept-Language") Locale locale){
		//validate if the user exist 
		if(userRepo.findByEmail(userSignUpRequest.getEmail()).isPresent()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("email.exisit.error", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.BAD_REQUEST) ; 
		}
		User user = new User() ; 
		user.setEmail(userSignUpRequest.getEmail());
		user.setFirstName(userSignUpRequest.getFirstName());
		user.setLastName(userSignUpRequest.getLastName()); 
		user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
		List<UserRoles> roles = new ArrayList<UserRoles>() ; 
		roles.add(new UserRoles("ROLE_USER"));
		user.setRoles(roles); 
		
		this.userRepo.save(user) ; 
		
		List<String> ls = new ArrayList<>() ; 
		ls.add(this.messageSource.getMessage("registeration.success", null, locale)) ; 
		return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.CREATED) ;
		
		
	}

}
