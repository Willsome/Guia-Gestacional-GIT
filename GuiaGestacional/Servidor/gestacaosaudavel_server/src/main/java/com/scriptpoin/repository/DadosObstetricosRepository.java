package com.scriptpoin.repository;

import com.scriptpoin.models.DadosObstetricos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosObstetricosRepository extends CrudRepository<DadosObstetricos, Long> {
}
