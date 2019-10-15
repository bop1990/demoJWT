package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Perfil implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5849953134334264738L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Modulo perfil;

	@ManyToOne
	@JsonIgnore
	private Usuario usuario;

	@Override
	public String getAuthority() {
		return perfil.toString();
	}

	public enum Modulo {
		ADMIN("ADMIN"), USUARIO("USUARIO");
		private String modulo;

		private Modulo(String modulo) {
			this.modulo = modulo;
		}

		@Override
		public String toString() {
			return this.modulo;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modulo getPerfil() {
		return perfil;
	}

	public void setPerfil(Modulo perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
