package com.autoexpres.repository;

import com.autoexpres.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // JpaRepository ya provee métodos CRUD básicos (save, findById, findAll, deleteById, etc.)
}