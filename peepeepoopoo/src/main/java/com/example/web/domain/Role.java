package com.example.web.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum Role implements GrantedAuthority {
    USER, TEACHER, SUPERUSER;

    @Override
    public String getAuthority() {
        return name();
    }
}
