package com.app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String name;

    private LocalDate dob;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate age) {
        this.dob = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", classRoom=" + classRoom +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
