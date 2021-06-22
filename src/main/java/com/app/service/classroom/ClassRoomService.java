package com.app.service.classroom;

import com.app.model.ClassRoom;
import com.app.model.Teacher;
import com.app.repository.IClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClassRoomService implements IClassRoomService{

    @Autowired
    IClassRoomRepository classRoomRepository;

    @Override
    public Iterable<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findById(Long id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public void save(ClassRoom classRoom) {
        classRoomRepository.save(classRoom);
    }

    @Override
    public void remove(Long id) {
        classRoomRepository.deleteById(id);
    }


}
