package com.app.repository;

import com.app.model.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends PagingAndSortingRepository<Teacher,Long> {


}
