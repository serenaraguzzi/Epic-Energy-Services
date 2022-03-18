package it.be.energy.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.energy.model.User;
import it.be.energy.service.UserService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Trova uno user tramite il suo ID di registrazione")
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findById(@PathVariable(required = true) Long id) {
		Optional<User> find = userService.findById(id);
		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}