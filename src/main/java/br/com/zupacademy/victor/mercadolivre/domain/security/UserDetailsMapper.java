package br.com.zupacademy.victor.mercadolivre.domain.security;

import org.springframework.security.core.userdetails.UserDetails;


public interface UserDetailsMapper {

	UserDetails map(Object shouldBeASystemUser);
}
