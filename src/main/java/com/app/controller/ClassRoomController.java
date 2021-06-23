package com.app.controller;

import com.app.exception.NotFoundException;
import com.app.model.ClassRoom;
import com.app.model.Student;
import com.app.service.classroom.IClassRoomService;
import com.app.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/classRoom")
public class ClassRoomController {

    @Autowired
    IClassRoomService classRoomService;

    @Autowired
    IStudentService studentService;

    @GetMapping("")
    public ModelAndView showList(){
        Iterable<ClassRoom> classRooms = classRoomService.findAll();
        ModelAndView modelAndView = new ModelAndView("/classroom/index");
        modelAndView.addObject("classRooms",classRooms);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/classroom/create");
        modelAndView.addObject("classRoom",new ClassRoom());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(@ModelAttribute ClassRoom classRoom, RedirectAttributes redirectAttributes){
        classRoomService.save(classRoom);
        redirectAttributes.addFlashAttribute("message","tạo mới thành công");
        ModelAndView modelAndView = new ModelAndView("redirect:/classRoom");
        return modelAndView;
    }
    @GetMapping("/findStudents/{id}")
    public ModelAndView listStudents(@PathVariable Long id) throws NotFoundException {
        Optional<ClassRoom> classRoom = classRoomService.findById(id);
        Iterable<Student> students = studentService.findAllByClassRoom(classRoom.get());
        ModelAndView modelAndView = new ModelAndView("classroom/showStudents");
        modelAndView.addObject("students",students);
        return modelAndView;
    }
}
