package com.app.repository;

import com.app.model.ClassRoom;
import com.app.model.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRoomRepository extends PagingAndSortingRepository<ClassRoom,Long> {

}
