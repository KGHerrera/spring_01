package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/")
	public String paginaInicio(Model model) {
		model.addAttribute("listaAlumnos", alumnoService.obtenerAlumnos());
		return "index";
	}

	
	@GetMapping("/agregar")
	public String agregar(Model modelo) {
	  modelo.addAttribute("alumno", new Alumno());
	  return "form";
	}
	
	@PostMapping(path="/guardar")
	public String guardar(@Validated Alumno a, Model model) {
		alumnoService.guardarAlumno(a);
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model modelo, @PathVariable String id) {
		alumnoService.eliminarAlumno(id);
		return "redirect:/"; 
	}
	
	@GetMapping("/editar/{id}")
    public String editar(Model modelo, @PathVariable String id) {
        Alumno alumno = alumnoService.obtenerAlumno(id);
        if (alumno != null) {
            modelo.addAttribute("alumno", alumno);
            return "form";
        } else {
            // Handle case where the alumno with the given id doesn't exist
            return "redirect:/";
        }
    }
}
