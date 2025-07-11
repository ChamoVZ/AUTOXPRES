package com.autoexpres.service;

import com.autoexpres.model.Vehiculo;
import com.autoexpres.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> findAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> findVehiculoById(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteVehiculoById(Long id) {
        vehiculoRepository.deleteById(id);
    }
}