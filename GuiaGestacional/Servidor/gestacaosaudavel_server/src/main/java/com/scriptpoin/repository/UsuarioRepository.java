package com.scriptpoin.repository;

import com.scriptpoin.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByUsuarioAndSenha(String usuario, String senha);

}
