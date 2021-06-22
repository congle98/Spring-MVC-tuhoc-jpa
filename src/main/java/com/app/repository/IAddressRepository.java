package com.app.repository;

import com.app.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
//repository để hiểu đây là lớp làm việc  với database và nó có thể xử lý các exception của database trên java
@Repository
public interface IAddressRepository extends PagingAndSortingRepository<Address,Long> {

}
