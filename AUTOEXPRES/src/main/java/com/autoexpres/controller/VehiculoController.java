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
    public String mostrarCotizacionPorsche911() {  
        return "vehiculos/porche_911";
    }
    
    @GetMapping("/cotizacion/touareg-r")
    public String mostrarCotizacionTouareg() {  
        return "vehiculos/Volkswagen_Touareg";
    }
    
    @GetMapping("/cotizacion/bmw-m850i")
    public String mostrarCotizacionBMWM850i() {  
        return "vehiculos/BMW_Serie8";
    }
    
    @GetMapping("/cotizacion/bmw-x6")
    public String mostrarCotizacionBMWX6() { 
        return "vehiculos/BMW_X6";
    }
    
    @GetMapping("/cotizacion/panamera")
    public String mostrarCotizacionPanamera() {
        return "vehiculos/Porshe_Panamera";
    }
    
    @GetMapping("/cotizacion/audi-a6")
    public String mostrarCotizacionAudiA6() {  
        return "vehiculos/Audi_A6";
    }
    
     // Endpoint para la renta
    @GetMapping("/alquiler")
    public String mostrarRenta() {  
        return "renta";
    }
}
