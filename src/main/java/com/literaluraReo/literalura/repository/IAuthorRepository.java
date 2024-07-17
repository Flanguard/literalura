package com.literaluraReo.literalura.repository;

import com.literaluraReo.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(Integer aNo, Integer aNo2);

    Optional<Author> findByNombreAndNacimiento(String nombre, Integer nacimiento);
}
