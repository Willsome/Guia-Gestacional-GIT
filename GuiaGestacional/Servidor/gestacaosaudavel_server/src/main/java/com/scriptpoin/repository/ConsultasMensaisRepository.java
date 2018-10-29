package com.scriptpoin.repository;

import com.scriptpoin.models.ConsultasMensais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultasMensaisRepository extends CrudRepository<ConsultasMensais, Long> {
}