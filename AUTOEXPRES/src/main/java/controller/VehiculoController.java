package controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VehiculoController {
    
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