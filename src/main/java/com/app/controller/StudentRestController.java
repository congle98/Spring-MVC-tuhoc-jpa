package com.app.controller;


import com.app.exception.NotFoundException;
import com.app.model.Student;
import com.app.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Entity;

@RestController
@RequestMapping("/restStudent")
public class StudentRestController {
    @Autowired
    IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> listStudent(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) throws NotFoundException {


        return new ResponseEntity<>(studentService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student,@PathVariable Long id){
        student.setId(id);
        studentService.save(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable  Long id){
        studentService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView error(){
        return new ModelAndView("error");
    }
}
