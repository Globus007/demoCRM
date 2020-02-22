package com.gleb.crm.controller;


import com.gleb.crm.entity.Employee;
import com.gleb.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ThymeleafController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("date", new Date());
        return "hello";
    }

    @GetMapping("/list")
    public String showListEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam int employeeId, Model model) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/list";
    }

}
