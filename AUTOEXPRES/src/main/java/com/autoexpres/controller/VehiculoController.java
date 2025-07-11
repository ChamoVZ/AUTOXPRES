package com.autoexpres.controller; // Asegúrate de que el paquete sea correcto, según tu proyecto

import com.autoexpres.model.Vehiculo; // Asume que Vehiculo está en este paquete
import com.autoexpres.service.VehiculoService; // Asume que VehiculoService está en este paquete
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("") // Mantenemos el RequestMapping a nivel de clase vacío para las rutas principales
public class VehiculoController {

    @Autowired // Inyecta el servicio para interactuar con los datos de vehículos
    private VehiculoService vehiculoService;

    // --- Rutas Públicas (existentes y ajustadas) ---

    // Endpoint para la página de inicio
    @GetMapping("/") // Se recomienda usar "/" para la página de inicio
    public String index() {
        return "index"; // Vista: templates/index.html (no necesitas "templates/" en el return)
    }

    // Endpoint para el catálogo principal
    @GetMapping("/catalogo")
    public String mostrarCatalogo() {
        return "vehiculos/Catalogo_Vehiculos"; // Vista: templates/vehiculos/Catalogo_Vehiculos.html
    }

    // Endpoint para la cotización (ejemplos, puedes generalizar si hay muchos)
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
        return "renta"; // Vista: templates/renta.html
    }


    // Endpoint para mostrar detalles de un vehículo específico del catálogo (usando ID)
    // Esto es útil si quieres tener páginas de detalles dinámicas para cada vehículo
    @GetMapping("/vehiculo/{id}")
    public String verDetallesVehiculo(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return vehiculoService.findVehiculoById(id).map(vehiculo -> {
            model.addAttribute("vehiculo", vehiculo);
            return "vehiculos/detalleVehiculo"; // Vista: templates/vehiculos/detalleVehiculo.html (tendrías que crearla)
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Vehículo no encontrado.");
            return "redirect:/catalogo"; // Redirige al catálogo general o a una página de error
        });
    }

    // --- Rutas de Administración CRUD para Vehículos ---

    // Listar todos los vehículos (READ)
    @GetMapping("/admin/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.findAllVehiculos());
        return "admin/vehiculos/listaVehiculos"; // Vista: templates/admin/vehiculos/listaVehiculos.html
    }

    // Mostrar formulario para crear un nuevo vehículo (CREATE - GET)
    @GetMapping("/admin/vehiculos/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("vehiculo", new Vehiculo()); // Objeto vacío para el formulario
        model.addAttribute("titulo", "Agregar Nuevo Vehículo");
        return "admin/vehiculos/formVehiculo"; // Vista: templates/admin/vehiculos/formVehiculo.html
    }

    // Guardar un nuevo vehículo o actualizar uno existente (CREATE/UPDATE - POST)
    @PostMapping("/admin/vehiculos/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, RedirectAttributes redirectAttributes) {
        vehiculoService.saveVehiculo(vehiculo);
        redirectAttributes.addFlashAttribute("mensaje", "Vehículo guardado exitosamente!");
        return "redirect:/admin/vehiculos"; // Redirige a la lista de vehículos
    }

    // Mostrar formulario para editar un vehículo existente (UPDATE - GET)
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
}

    
   


