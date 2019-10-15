package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.UsuarioDTO;

import io.swagger.annotations.ApiOperation;

@RestController
public class LoginController {
	
	@ApiOperation("Login.")
	@PostMapping("/login")
	public void fakeLogin(@RequestBody UsuarioDTO credentials) {
	    throw new IllegalStateException("Método não executado. Implementado pelo filtro do Spring Security.");
	}

//	@ApiOperation("Logout.")
//	@PostMapping("/logout")
//	public void fakeLogout() {
//	    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
//	}

}
