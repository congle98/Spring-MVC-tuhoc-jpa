package com.app.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class TeacherForm {
    private Long id;
    private String name;
    private LocalDate dob;
    private MultipartFile image;
    private List<ClassRoom> classRoomList;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<ClassRoom> getClassRoomList() {
        return classRoomList;
    }

    public void setClassRoomList(List<ClassRoom> classRoomList) {
        this.classRoomList = classRoomList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
