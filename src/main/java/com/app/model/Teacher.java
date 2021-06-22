package com.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(name = "teacher_class",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    Set<ClassRoom> classRoomList = new HashSet<>();

    private String name;

    private LocalDate dob;

    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<ClassRoom> getClassRoomList() {
        return classRoomList;
    }

    public void setClassRoomList(Set<ClassRoom> classRoomList) {
        this.classRoomList = classRoomList;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", address=" + address +
                ", classRoomList=" + classRoomList +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", image='" + image + '\'' +
                '}';
    }
}
