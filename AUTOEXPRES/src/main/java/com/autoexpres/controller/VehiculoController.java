package com.autoexpres.controller; 

import com.autoexpres.model.Vehiculo; 
import com.autoexpres.service.VehiculoService; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;



@Controller
@RequestMapping("")

public class VehiculoController {

    @Autowired 
    private VehiculoService vehiculoService;

   
    @GetMapping("/")
    public String index() {
        return "index"; 
    }

   
    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        List<Vehiculo> vehiculos = vehiculoService.findAllVehiculos();

        model.addAttribute("vehiculos", vehiculos); 
        return "vehiculos/Catalogo_Vehiculos"; 
    }


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
    
    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "sobreNosotros"; 
    }


    
    @GetMapping("/vehiculo/{id}")
public String verDetalleVehiculo(@PathVariable Long id, Model model) {
    Optional<Vehiculo> vehiculoOptional = vehiculoService.findVehiculoById(id);
    if (vehiculoOptional.isEmpty()) {
        return "redirect:/vehiculos/catalogo";
    }
    Vehiculo vehiculo = vehiculoOptional.get();
    model.addAttribute("vehiculo", vehiculo);
    return "vehiculos/detalleVehiculo";
}

@GetMapping("/admin/vehiculos/detalle/{id}")
public String verDetallesVehiculo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
    return vehiculoService.findVehiculoById(id).map(vehiculo -> {
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculos/detalleVehiculo"; // Podría ser una plantilla diferente, ej. "admin/vehiculos/detalleAdmin"
    }).orElseGet(() -> {
        redirectAttributes.addFlashAttribute("error", "Vehículo no encontrado.");
        return "redirect:/admin/vehiculos"; // Redirigir a la lista de admin si no se encuentra
    });
}



    // Listar todos los vehículos (READ)
    @GetMapping("/admin/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.findAllVehiculos());
        return "admin/vehiculos/listaVehiculos";
    }

    // Mostrar formulario para crear un nuevo vehículo (CREATE - GET)
    @GetMapping("/admin/vehiculos/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("titulo", "Agregar Nuevo Vehículo");
        return "admin/vehiculos/formVehiculo"; 
    }

    // Guardar un nuevo vehículo o actualizar uno existente (CREATE/UPDATE - POST)
//    @PostMapping("/admin/vehiculos/guardar")
//    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, RedirectAttributes redirectAttributes) {
//        vehiculoService.saveVehiculo(vehiculo);
//        redirectAttributes.addFlashAttribute("mensaje", "Vehículo guardado exitosamente!");
//        return "redirect:/admin/vehiculos"; // Redirige a la lista de vehículos
//    }
    @PostMapping("/admin/vehiculos/guardar")
public String guardarVehiculo(@Valid @ModelAttribute("vehiculo") Vehiculo vehiculo,
                              BindingResult result, 
                              RedirectAttributes redirectAttributes,
                              Model model) { 

    if (result.hasErrors()) {
        
        model.addAttribute("titulo", vehiculo.getId() == null ? "Agregar Nuevo Vehículo" : "Editar Vehículo");
        return "admin/vehiculos/formVehiculo";
    }

    vehiculoService.saveVehiculo(vehiculo);
    redirectAttributes.addFlashAttribute("mensaje", "Vehículo guardado exitosamente!");
    return "redirect:/admin/vehiculos"; // Redirige a la lista de vehículos
}
    

    @GetMapping("/admin/vehiculos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return vehiculoService.findVehiculoById(id).map(vehiculo -> {
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("titulo", "Editar Vehículo");
            return "admin/vehiculos/formVehiculo"; // Vista: templates/admin/vehiculos/formVehiculo.html
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Vehículo no encontrado para edición.");
            return "redirect:/admin/vehiculos";
        });
    }

    // Eliminar un vehículo (DELETE)
    @GetMapping("/admin/vehiculos/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            vehiculoService.deleteVehiculoById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Vehículo eliminado exitosamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el vehículo: " + e.getMessage());
        }
        return "redirect:/admin/vehiculos";
    }
   // @GetMapping("/catalogo")
   // public String mostrarCatalogo(Model model) {
        // Obtiene todos los vehículos del servicio
   //     List<Vehiculo> vehiculos = vehiculoService.findAllVehiculos();
        // Agrega la lista al modelo con el nombre "vehiculos"
   //     model.addAttribute("vehiculos", vehiculos); 
  //      return "vehiculos/Catalogo_Vehiculos";
  //  }
}

    
   


