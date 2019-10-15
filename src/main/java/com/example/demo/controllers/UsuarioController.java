package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/api/usuarios", method = RequestMethod.GET)
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	@RequestMapping(value = "/api/usuario/{email}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "email") String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if (usuario.isPresent()) {
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/addUsuario", method = RequestMethod.POST)
	public Usuario inserirUsuario(@Valid @RequestBody Usuario usuario) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	@RequestMapping(value = "/api/atualizaUsuario/{email}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "email") String email, @Valid @RequestBody Usuario newUsuario) {
		Optional<Usuario> oldUsuario = usuarioRepository.findByEmail(email);
		if (oldUsuario.isPresent()) {
			Usuario usuario = oldUsuario.get();
			usuario.setEmail(newUsuario.getEmail());
			usuarioRepository.save(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/usuario/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "email") String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		if (usuario.isPresent()) {
			usuarioRepository.delete(usuario.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
