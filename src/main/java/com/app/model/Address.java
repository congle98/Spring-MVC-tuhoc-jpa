package com.app.model;




import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Long thì nếu ko có nó sẽ trả về null
    // còn kiểu thường như long hay int thì ko có nó sẽ trả về kiểu mặc định là 0


    //jsonIgnore để tránh xung đột lấy json
    @OneToMany(targetEntity = Student.class)
    @JsonIgnore
    private List<Student> students;

    @OneToMany(targetEntity = Teacher.class)
    @JsonIgnore
    private List<Teacher> teachers;



    @Size(min = 2, max = 30,message = "{nameError}")
    private String name;

    public Address() {
    }

    public Address(Long id, List<Student> students, List<Teacher> teachers,@NotEmpty @Size(min = 2, max = 30) String name) {
        this.id = id;
        this.students = students;
        this.teachers = teachers;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", students=" + students +
                ", name='" + name + '\'' +
                '}';
    }
}
