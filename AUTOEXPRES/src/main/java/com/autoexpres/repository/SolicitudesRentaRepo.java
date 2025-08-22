package com.autoexpres.repository;

import com.autoexpres.model.SolicitudesRenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitudesRentaRepo  extends JpaRepository<SolicitudesRenta, Long> {
    List<SolicitudesRenta> findByEmail(String email);
    List<SolicitudesRenta> findByVehiculoInteresContaining(String vehiculo);
}