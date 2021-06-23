package com.app.controller;


import com.app.exception.NotFoundException;
import com.app.model.Address;
import com.app.model.Student;
import com.app.service.address.IAddressService;
import com.app.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @Autowired
    IStudentService studentService;

    @GetMapping("")
    public ModelAndView addressList(){
        Iterable<Address> addresses = addressService.findAll();
        ModelAndView modelAndView = new ModelAndView("address/index");
        modelAndView.addObject("addresses",addresses);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("address/create");
        modelAndView.addObject("address",new Address());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView save(@Validated @ModelAttribute("address") Address address, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("address/create");
            return modelAndView;
        }
        else {
            addressService.save(address);
            ModelAndView modelAndView = new ModelAndView("address/create");
            modelAndView.addObject("address",new Address());
            modelAndView.addObject("message","tao moi thanh cong");
            return modelAndView;
        }

    }

    @GetMapping("/findStudents/{id}")
    public ModelAndView showStudents(@PathVariable Long id) throws NotFoundException {
        Optional<Address> address = addressService.findById(id);
        Iterable<Student> students = studentService.findAllByAddress(address.get());
        ModelAndView modelAndView = new ModelAndView("address/showStudents");
        modelAndView.addObject("students",students);
        return modelAndView;
    }
}
