package com.app.controller;


import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.model.Teacher;
import com.app.service.address.IAddressService;
import com.app.service.classroom.IClassRoomService;
import com.app.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;

    @Autowired
    IAddressService addressService;

    @Autowired
    IClassRoomService classRoomService;

    @ModelAttribute("addressList")
    public Iterable<Address> addresses(){
        return addressService.findAll();
    }
    @ModelAttribute("classRoomList")
    public Iterable<ClassRoom> classRooms(){
        return classRoomService.findAll();
    }


}
