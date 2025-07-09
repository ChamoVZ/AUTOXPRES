package com.autoexpres.controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class VehiculoController {
    @GetMapping("/index")
    public String Index() {
        return "templates/index"; 
    }
    
    // Endpoint para el catálogo principal
    @GetMapping("/catalogo")
    public String mostrarCatalogo() {
        return "vehiculos/Catalogo_Vehiculos"; 
    }

    // Endpoint para la cotización
    @GetMapping("/cotizacion/porsche-911")
    public String mostrarCotizacionPorche() {
        return "vehiculos/cotizacion_porsche";
    }
}