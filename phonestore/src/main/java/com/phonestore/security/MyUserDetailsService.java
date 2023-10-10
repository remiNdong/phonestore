package com.phonestore.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phonestore.administration.domain.User;
import com.phonestore.administration.service.UserService;

/*
 * Classe qui permet de voir si un User existe, de charger ses droits grace à ses Roles  et de recuperer ses UserDetails
 * Sera utilisée par Spring security
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Utilisateur introuvable !");
		List<GrantedAuthority> auths = new ArrayList<>();
		user.getRoles().forEach(role -> {
			GrantedAuthority auhority = new SimpleGrantedAuthority(role.getRole());
			auths.add(auhority);
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auths);
	}
}