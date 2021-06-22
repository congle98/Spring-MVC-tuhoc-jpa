package com.app.repository;

import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student,Long> {
    Iterable<Student> findAllByAddress(Address address);
    Iterable<Student> findAllByClassRoom(ClassRoom classRoom);
}
