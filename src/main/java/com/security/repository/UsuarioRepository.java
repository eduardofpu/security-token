package com.security.repository;

import com.security.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository  extends PagingAndSortingRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
