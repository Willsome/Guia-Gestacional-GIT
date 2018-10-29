package com.scriptpoin.repository;

import com.scriptpoin.models.DadosPessoais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosPessoaisRepository extends CrudRepository<DadosPessoais, Long> {
}
