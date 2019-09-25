package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public String getUsuarios() {
		return "{\"usuarios\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
		           "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
	}

}
