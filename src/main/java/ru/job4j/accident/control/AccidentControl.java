package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidents;

    public AccidentControl(AccidentService accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.getAllAccidentTypes());
        model.addAttribute("rules", accidents.getAllRules());
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidents.getAllAccidentTypes());
        model.addAttribute("rules", accidents.getAllRules());
        model.addAttribute("accident", accidents.getAccident(id));
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidents.saveAccident(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidents.saveAccident(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String update(@RequestParam("id") int id) {
        accidents.removeAccident(id);
        return "redirect:/";
    }
}