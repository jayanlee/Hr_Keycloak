package com.stpl.gtn.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stpl.gtn.domain.DashBoard;

@Repository
public interface DashboardRepository extends MongoRepository<DashBoard, String>{

}
