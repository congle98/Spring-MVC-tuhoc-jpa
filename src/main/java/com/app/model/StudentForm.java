package com.app.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class StudentForm {
    private Long id;
    private String name;
    private LocalDate dob;
    private MultipartFile image;
    private ClassRoom classRoom;
    private Address address;

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

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
