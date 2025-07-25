package com.autoexpres.service;

import com.autoexpres.model.Renta;
import com.autoexpres.repository.RentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RentaService {

    @Autowired
    private RentaRepository rentaRepository;

    public List<Renta> findAllRentas() {
        return rentaRepository.findAll();
    }

    public Optional<Renta> findRentaById(Long id) {
        return rentaRepository.findById(id);
    }

    public Renta saveRenta(Renta renta) {
        return rentaRepository.save(renta);
    }

    public void deleteRentaById(Long id) {
        rentaRepository.deleteById(id);
    }
}