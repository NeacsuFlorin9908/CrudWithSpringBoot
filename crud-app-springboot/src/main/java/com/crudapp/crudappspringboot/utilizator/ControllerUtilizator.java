package com.crudapp.crudappspringboot.utilizator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerUtilizator {
    @Autowired private ServiceUtilizator service;

    @GetMapping("/utilizatori")
    public String afiseasaListaUtilizatori(Model model){
        List<Utilizator> listaUtilizatori = service.afiseazaListaUtilizatori();
        model.addAttribute("listaUtilizatori", listaUtilizatori);

        return "utilizatori";
    }

    @GetMapping("/utilizatori/nou")
    public String afiseasaFormularNou(Model model){
        model.addAttribute("utilizator", new Utilizator());
        model.addAttribute("pageTitle", "Adauga Utilizator Nou");
        return "formular_utilizator";
    }

    @PostMapping("/utilizatori/salveaza")
    public String salveazaUtilizator(Utilizator utilizator, RedirectAttributes redirectAttributes){
        service.salveaza(utilizator);
        redirectAttributes.addFlashAttribute("message", "Utilizatorul a fost adăugat/modificat cu succes!");
        return "redirect:/utilizatori";
    }

    @GetMapping("/utilizatori/modifica/{id}")
    public String afiseazaFormularEditare(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Utilizator utilizator =service.get(id);
            model.addAttribute("utilizator", utilizator);
            model.addAttribute("pageTitle", "Editează Utilizatorul cu ID-ul :" + id);
            return "formular_utilizator";
        } catch (ExceptieUtitilizatorulNuEsteGasit e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/utilizatori";
        }

    }

    @GetMapping("/utilizatori/sterge/{id}")
    public String stergeUtilizator(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            service.sterge(id);
            redirectAttributes.addFlashAttribute("message", "Utilizatorul cu ID-ul" + id + " a fost sters cu succes!");
        } catch (ExceptieUtitilizatorulNuEsteGasit e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/utilizatori";


    }
}
