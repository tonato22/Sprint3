package com.exemplo.patio.service;

import com.exemplo.patio.model.Usuario;
import com.exemplo.patio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // ⚠️ IMPORTANTE sobre roles:
        // - Se você guarda "ADMIN" / "USER" no banco, use .roles(...).
        // - Se você guarda "ROLE_ADMIN" / "ROLE_USER", use .authorities(...).
        // Aqui assumo que você guarda SEM o prefixo "ROLE_":
        String[] roles = usuario.getRoles().toArray(new String[0]);

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getSenha())          // senha já deve estar BCRYPT
                .roles(roles)                          // adiciona "ROLE_" automaticamente
                .disabled(!usuario.isAtivo())
                .accountLocked(!usuario.isAtivo())
                .build();
    }
}