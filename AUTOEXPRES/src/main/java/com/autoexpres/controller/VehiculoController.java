package com.autoexpres.controller; 

import com.autoexpres.model.Vehiculo;
import com.autoexpres.model.Renta; 
import com.autoexpres.model.SolicitudesRenta; // Nombre cambiado
import com.autoexpres.repository.SolicitudesRentaRepo; // Nombre cambiado
import com.autoexpres.service.VehiculoService;
import com.autoexpres.service.RentaService; 
import com.autoexpres.model.CotizacionVehiculo;
import com.autoexpres.repository.CotizacionVehiculoRepository;
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

    @Autowired
    private RentaService rentaService;
    
    @Autowired
    private SolicitudesRentaRepo solicitudesRentaRepo; // Nombre cambiado
    
    @Autowired 
    private CotizacionVehiculoRepository cotizacionVehiculoRepository;

    @GetMapping("/")
    public String index() {
        return "index"; 
    }
    
    @GetMapping("http://localhost:90/")
    public String local() {
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

    // Endpoint para la renta
    @GetMapping("/alquiler")
    public String mostrarRenta(Model model) {
        List<Renta> rentas = rentaService.findAllRentas();
        model.addAttribute("rentas", rentas);
        return "renta"; 
    }

    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "sobreNosotros"; 
    }
    
    @GetMapping("/contactenos")
    public String mostrarcontactenos() {
        return "contactenos"; 
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
            return "vehiculos/detalleVehiculo";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Vehículo no encontrado.");
            return "redirect:/admin/vehiculos";
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
        return "redirect:/admin/vehiculos";
    }

    @GetMapping("/admin/vehiculos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return vehiculoService.findVehiculoById(id).map(vehiculo -> {
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("titulo", "Editar Vehículo");
            return "admin/vehiculos/formVehiculo";
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

    @GetMapping("/admin/rentas")
    public String listarRentas(Model model) {
        model.addAttribute("rentas", rentaService.findAllRentas());
        return "admin/rentas/listaRentas";
    }

    @GetMapping("/admin/rentas/nuevo")
    public String mostrarFormularioRenta(Model model) {
        model.addAttribute("renta", new Renta());
        model.addAttribute("vehiculos", vehiculoService.findAllVehiculos());
        model.addAttribute("titulo", "Nueva Renta");
        return "admin/rentas/formRenta";
    }

    @PostMapping("/admin/rentas/guardar")
    public String guardarRenta(@Valid @ModelAttribute Renta renta, 
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("vehiculos", vehiculoService.findAllVehiculos());
            model.addAttribute("titulo", renta.getId() == null ? "Nueva Renta" : "Editar Renta");
            return "admin/rentas/formRenta";
        }
        
        rentaService.saveRenta(renta);
        redirectAttributes.addFlashAttribute("mensaje", "Renta guardada exitosamente");
        return "redirect:/admin/rentas";
    }

    @GetMapping("/admin/rentas/editar/{id}")
    public String editarRenta(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Renta> renta = rentaService.findRentaById(id);
        
        if (renta.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Renta no encontrada");
            return "redirect:/admin/rentas";
        }
        
        model.addAttribute("renta", renta.get());
        model.addAttribute("vehiculos", vehiculoService.findAllVehiculos());
        model.addAttribute("titulo", "Editar Renta");
        return "admin/rentas/formRenta";
    }

    @GetMapping("/admin/rentas/eliminar/{id}")
    public String eliminarRenta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            rentaService.deleteRentaById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Renta eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar renta: " + e.getMessage());
        }
        return "redirect:/admin/rentas";
    }
    
    @GetMapping("/admin/rentas/detalle/{id}")
    public String detalleRenta(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return rentaService.findRentaById(id).map(renta -> {
            model.addAttribute("renta", renta);
            return "admin/rentas/detalleRenta";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Renta no encontrada");
            return "redirect:/admin/rentas";
        });
    }

    // ===== MÉTODOS PARA SOLICITUDES DE RENTA =====

    // Mostrar formulario de solicitud de información
    @GetMapping("/renta/solicitar/{vehiculo}")
    public String mostrarFormularioSolicitud(@PathVariable String vehiculo, Model model) {
        SolicitudesRenta solicitud = new SolicitudesRenta(); // Nombre cambiado
        solicitud.setVehiculoInteres(vehiculo);
        model.addAttribute("solicitud", solicitud);
        return "admin/rentas/FormRentaSolicitudes";
    }

    // Procesar el formulario de solicitud
  @PostMapping("/renta/solicitar")
public String procesarSolicitud(@Valid @ModelAttribute("solicitud") SolicitudesRenta solicitud, 
                              BindingResult result, 
                              RedirectAttributes redirectAttributes) {
    
    if (result.hasErrors()) {
        return "admin/rentas/FormRentaSolicitudes";
    }
    
    solicitudesRentaRepo.save(solicitud);
    redirectAttributes.addFlashAttribute("mensaje", "¡Solicitud enviada con éxito! Nos contactaremos contigo pronto.");
    return "redirect:/alquiler";
}

    // Para la sección administrativa - ver todas las solicitudes
    @GetMapping("/admin/solicitudes")
    public String listarSolicitudes(Model model) {
        model.addAttribute("solicitudes", solicitudesRentaRepo.findAll()); // Nombre cambiado
        return "admin/rentas/ListaRentaSolicitudes";
    }
    
     // ===== MÉTODOS PARA COTIZACIONES DE VEHÍCULOS =====
    @GetMapping("/cotizacion/{vehiculo}")
public String mostrarFormularioCotizacion(@PathVariable String vehiculo, Model model) {
    CotizacionVehiculo cotizacion = new CotizacionVehiculo();
    cotizacion.setVehiculoInteres(vehiculo);
    model.addAttribute("cotizacion", cotizacion);
    return "vehiculos/formCotizacion"; // Para formulario genérico
}

// Nueva ruta específica para Audi A6
@GetMapping("/cotizacion/audi-a6")
public String mostrarAudiA6(Model model) {
    CotizacionVehiculo cotizacion = new CotizacionVehiculo();
    cotizacion.setVehiculoInteres("Audi A6 Avant");
    model.addAttribute("cotizacion", cotizacion);
    return "vehiculos/Audi_A6"; // Para página específica con formulario integrado
}

@PostMapping("/cotizacion/enviar")
public String procesarCotizacion(@Valid @ModelAttribute("cotizacion") CotizacionVehiculo cotizacion, 
                               BindingResult result, 
                               RedirectAttributes redirectAttributes) {
    
    if (result.hasErrors()) {
        // Determinar a qué página regresar basado en el vehículo
        if ("Audi A6 Avant".equals(cotizacion.getVehiculoInteres())) {
            return "vehiculos/Audi_A6";
        } else {
            return "vehiculos/formCotizacion";
        }
    }
    
    cotizacionVehiculoRepository.save(cotizacion);
    redirectAttributes.addFlashAttribute("mensaje", "¡Cotización enviada con éxito! Nos contactaremos contigo pronto.");
    return "redirect:/catalogo";
}

@GetMapping("/admin/cotizaciones")
public String listarCotizaciones(Model model) {
    model.addAttribute("cotizaciones", cotizacionVehiculoRepository.findAll());
    return "admin/cotizaciones/listaCotizaciones";
}
}