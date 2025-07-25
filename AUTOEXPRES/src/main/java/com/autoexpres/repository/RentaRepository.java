package com.autoexpres.repository;

import com.autoexpres.model.Renta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentaRepository extends JpaRepository<Renta, Long> {
    // Hereda automáticamente todos los métodos CRUD básicos de JpaRepository:
    // save(), findById(), findAll(), deleteById(), etc.
}