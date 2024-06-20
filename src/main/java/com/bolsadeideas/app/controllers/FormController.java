package com.bolsadeideas.app.controllers;

import com.bolsadeideas.app.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("titulo", "Formulario usuario");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesarInfo(@Valid Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo: " + err.getField() + " " + err.getDefaultMessage());
            });
            model.addAttribute("error", errores);
            return "form";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Resultado del form");
        return "resultado";
    }


}
