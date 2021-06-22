package com.app.service.teacher;

import com.app.model.Teacher;
import com.app.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService implements ITeacherService{

    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void remove(Long id) {
        teacherRepository.deleteById(id);
    }
}
