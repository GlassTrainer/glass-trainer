package com.glasstrainer.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/current", method = RequestMethod.GET, produces = "application/json")
	public UserDetails getCurrentUserInformation() {


		return getUser();
	}

	@RequestMapping(value = "/authenticated", method = RequestMethod.GET, produces = "application/json")
	public UserDetails authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		return (UserDetails)authentication.getPrincipal();
	}

	private UserDetails getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
			return null;
		}

		return (UserDetails)authentication.getPrincipal();
	}
}