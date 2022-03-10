package com.cheatHub.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cheatHub.entities.Usuario;

import com.cheatHub.services.ServicioUsuario;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private ServicioUsuario userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = userService.getUsuarioByUsername(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		

		return new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), 
				user.getContraseña(), roles);

	}
}

