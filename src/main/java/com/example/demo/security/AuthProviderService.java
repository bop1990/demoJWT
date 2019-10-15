package com.example.demo.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Component
public class AuthProviderService implements AuthenticationProvider {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String senha = authentication.getCredentials().toString();

		// Defina suas regras para realizar a autenticação
		
		Optional<Usuario> usuarioBd = usuarioRepository.findByEmail(email);

		if (usuarioBd.isPresent()) {
			if (usuarioBd.get().isEnabled()) {

				if (passwordEncoder.matches(senha, usuarioBd.get().getPassword())) {
					Collection<? extends GrantedAuthority> perfis = usuarioBd.get().getAuthorities();
					return new UsernamePasswordAuthenticationToken(email, senha, perfis);
				}
			} else {
				throw new BadCredentialsException("Este usuário está desativado.");
			}
		}

		throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
