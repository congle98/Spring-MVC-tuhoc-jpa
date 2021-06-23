package com.app.controller;


import com.app.exception.NotFoundException;
import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.model.Student;
import com.app.model.StudentForm;
import com.app.service.address.IAddressService;
import com.app.service.classroom.IClassRoomService;
import com.app.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Value("${file-upload}")
    private  String fileUpload;

    @Autowired
    IStudentService studentService;

    @Autowired
    IClassRoomService classRoomService;

    @Autowired
    IAddressService addressService;

    @ModelAttribute("addressList")
    public Iterable<Address> addresses(){
        return addressService.findAll();
    }
    @ModelAttribute("classRoomList")
    public Iterable<ClassRoom> classRooms(){
        return classRoomService.findAll();
    }

//    @GetMapping("")
//    public ModelAndView studentList(String message){
//        Iterable<Student> students = studentService.findAll();
//        ModelAndView modelAndView = new ModelAndView("student/index");
//        modelAndView.addObject("message",message);
////        TEST CÁCH NHẬN MESSAGE TỪ 1 PHƯƠNG THỨC MODEL AND VIEW KHÁC THÀNH CÔNG
//        modelAndView.addObject("students",students);
//        return  modelAndView;
//    }
    @GetMapping("")
    public ModelAndView studentList(){
        Iterable<Student> students = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("student/index");
        modelAndView.addObject("students",students);
        return  modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("studentForm",new StudentForm());
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView createStudent(StudentForm studentForm){
        MultipartFile multipartFile = studentForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(studentForm.getImage().getBytes(),new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setName(studentForm.getName());
        student.setAddress(studentForm.getAddress());
        student.setClassRoom(studentForm.getClassRoom());
        student.setDob(studentForm.getDob());
        student.setImage(fileName);
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("student/create");
        modelAndView.addObject("studentForm",new StudentForm());
        modelAndView.addObject("message","tao moi thanh cong");
        return  modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView("student/edit");
        Optional<Student> student = studentService.findById(id);
        StudentForm studentForm = new StudentForm();
        studentForm.setId(id);
        studentForm.setName(student.get().getName());
        studentForm.setDob(student.get().getDob());
        studentForm.setAddress(student.get().getAddress());
        studentForm.setClassRoom(student.get().getClassRoom());
        modelAndView.addObject("studentForm",studentForm);
        return modelAndView;
    }

    @PostMapping("/update")
    private String update(StudentForm studentForm, RedirectAttributes redirectAttributes) throws NotFoundException {
        Optional<Student> student = studentService.findById(studentForm.getId());
        student.get().setName(studentForm.getName());
        student.get().setAddress(studentForm.getAddress());
        student.get().setClassRoom(studentForm.getClassRoom());
        student.get().setDob(studentForm.getDob());
        if(studentForm.getImage().getSize()!=0){
            MultipartFile multipartFile = studentForm.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(studentForm.getImage().getBytes(),new File(fileUpload+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.get().setImage(fileName);
        }
        studentService.save(student.get());
            redirectAttributes.addFlashAttribute("message","update thanh cong");
        return  "redirect:/student";
    }

                    ///TEST 2 CÁCH GỬI ĐI REDIRECT, NẾU GỬI BẰNG MODELANDVIEW THÌ PHẢI CHUYỀN THEO MESSAGE ĐỒNG THỜI BÊN KIA PHẢI NHẬN MESSAGE
//    @PostMapping("/update")
//    private ModelAndView update(StudentForm studentForm){
//        Optional<Student> student = studentService.findById(studentForm.getId());
//        student.get().setName(studentForm.getName());
//        student.get().setAddress(studentForm.getAddress());
//        student.get().setClassRoom(studentForm.getClassRoom());
//        student.get().setDob(studentForm.getDob());
//        ModelAndView modelAndView = new ModelAndView("redirect:/student");
//        if(studentForm.getImage().getSize()!=0){
//            MultipartFile multipartFile = studentForm.getImage();
//            String fileName = multipartFile.getOriginalFilename();
//            try {
//                FileCopyUtils.copy(studentForm.getImage().getBytes(),new File(fileUpload+fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            student.get().setImage(fileName);
//        }
//        studentService.save(student.get());
//        modelAndView.addObject("message","update thanh cong");
//        return  modelAndView;
//    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) throws NotFoundException {
        Optional<Student> student = studentService.findById(id);
        ModelAndView modelAndView = new ModelAndView("student/delete");
        modelAndView.addObject("student",student);
        return modelAndView;
    }

//    @PostMapping("/delete")
//    public  ModelAndView delete(Long id ){
//        studentService.remove(id);
//        ModelAndView modelAndView = new ModelAndView("student/index");
//        modelAndView.addObject("message","xoa thanh cong");
//        return modelAndView;
//    }


//    dùng cách này để gọi đến một phương thức khác của controller
    @PostMapping("/delete")
    public  String delete(Long id , RedirectAttributes redirectAttributes){
        studentService.remove(id);
        redirectAttributes.addFlashAttribute("message","xoa thanh cong");
        return "redirect:/student";
    }

    //hoặc cách này cũng oke ko khuyến cáo
//        @PostMapping("/delete")
//        public  ModelAndView deletetest(Long id ){
//            studentService.remove(id);
//            return studentList();
//    }


    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException(){
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
