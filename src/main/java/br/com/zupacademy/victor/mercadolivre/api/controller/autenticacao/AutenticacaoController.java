package br.com.zupacademy.victor.mercadolivre.api.controller.autenticacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.victor.mercadolivre.api.dto.request.LoginRequest;
import br.com.zupacademy.victor.mercadolivre.domain.security.TokenManager;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private static final Logger log = LoggerFactory
            .getLogger(AutenticacaoController.class);
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginInfo) {

        UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();

        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jwt = tokenManager.generateToken(authentication);

            return ResponseEntity.ok(jwt);

        } catch (AuthenticationException e) {
            log.error("[Authentication] {}", e);
            return ResponseEntity.badRequest().build();
        }

    }
}
