package com.example.jwt.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.Model.MrRegistrationModel;
import com.example.jwt.Repository.RegistrationRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MainController {

	@Autowired
	RegistrationRepo repo;
	
	//@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping({"/register"})
	public MrRegistrationModel home(@RequestBody MrRegistrationModel model) {
		MrRegistrationModel model2 = repo.save(model);
		return model2;
	}
	
	@GetMapping({"/getAllUser"})
	public List<MrRegistrationModel> getAllUser() {
		List<MrRegistrationModel> model2 = repo.findAll();
		return model2; 
	}
	
	@GetMapping({"/searchById/{id}"})
	public Optional<MrRegistrationModel> getAllUser(@PathVariable("id") Long id) { 
		return repo.findById(id); 
	}
	
	@DeleteMapping({"/delete/{id}"})
	public void delete(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}
	
	@GetMapping({"getCsrfToken"})
	public CsrfToken getCsrfToken(HttpServletRequest req) { 
		return (CsrfToken) req.getAttribute("_csrf");
	}
}
