/**
 * @Author:Serhat CAN
 */
package com.glasstrainer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable, UserDetails {

    /**
     * Default
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(length = 80, nullable = false)
    private String password;

    //	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]", message = "Invalid email address.")
    @Column(unique = true, length = 50)
    private String email;

    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters long.")
    private String firstname;

    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters long.")
    private String surname;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<Role>();

    protected User() {
        /* Reflection instantiation */
    }

    public User(String name, String passwordHash) {
        this.username = name;
        this.password = passwordHash;
    }

/*	public User(String name, String passwordHash, String email, String firstname, String surname, Role role) {
		this.username = name;
		this.password = passwordHash;
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
		this.addRole(role);
	}*/


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    //@JsonIgnore
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Set<Role> roles = this.getRoles();

        if (roles == null) {
            return Collections.emptyList();
        }

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
