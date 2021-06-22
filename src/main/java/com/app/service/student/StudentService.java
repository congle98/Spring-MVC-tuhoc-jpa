package com.app.service.student;

import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.model.Student;
import com.app.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements IStudentService{
    @Autowired
    IStudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findAllByClassRoom(ClassRoom classRoom) {
        return studentRepository.findAllByClassRoom(classRoom);
    }

    @Override
    public Iterable<Student> findAllByAddress(Address address) {
        return studentRepository.findAllByAddress(address);
    }
}
