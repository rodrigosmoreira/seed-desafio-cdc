package com.seeddesafiocdc.repository;

import java.util.Optional;

import com.seeddesafiocdc.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    Optional<Autor> findByEmail(String email);
    Optional<Autor> findByCpf(Long cpf);
}
