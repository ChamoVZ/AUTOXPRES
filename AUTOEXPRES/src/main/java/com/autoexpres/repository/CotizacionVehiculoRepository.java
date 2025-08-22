package com.autoexpres.repository;

import com.autoexpres.model.CotizacionVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CotizacionVehiculoRepository extends JpaRepository<CotizacionVehiculo, Long> {
    List<CotizacionVehiculo> findByEmail(String email);
    List<CotizacionVehiculo> findByVehiculoInteresContaining(String vehiculo);
}