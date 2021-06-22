package com.app.service.student;

import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.model.Student;
import com.app.service.IGeneralService;

import java.util.List;

public interface IStudentService extends IGeneralService<Student> {
    public Iterable<Student> findAllByAddress(Address address);
    public Iterable<Student> findAllByClassRoom(ClassRoom classRoom);
}
