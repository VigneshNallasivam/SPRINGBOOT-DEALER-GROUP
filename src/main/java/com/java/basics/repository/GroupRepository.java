package com.java.basics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.basics.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long>{

	void save(List<Group> dealer2);


}
