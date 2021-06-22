package com.app.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ClassRoom")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = Student.class)
    List<Student> students;
    @ManyToMany
    @JoinTable(name = "teacher_class",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    Set<Teacher> teachers = new HashSet<>();
    private String name;

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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", students=" + students +
                ", name='" + name + '\'' +
                '}';
    }
}
