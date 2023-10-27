package com.example.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.Physio;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    private String role;

    public UserDetailsImpl(Doctor doctor, String role) {
        this.email = doctor.getEmail();
        this.password = doctor.getPassword();
        this.role = role;
    }
    
    public UserDetailsImpl(Patient patient, String role) {
        this.email = patient.getEmail();
        this.password = patient.getPassword();
        this.role = role;
    }
    
    public UserDetailsImpl(Nurse nurse, String role) {
        this.email = nurse.getEmail();
        this.password = nurse.getPassword();
        this.role = role;
    }
    
    public UserDetailsImpl(Physio physio, String role) {
        this.email = physio.getEmail();
        this.password = physio.getPassword();
        this.role = role;
    }
    
    
    public UserDetailsImpl() {
     super();
    }
    

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
