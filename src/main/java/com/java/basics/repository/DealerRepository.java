package com.java.basics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.basics.entity.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer,Long> {

//	@Query(value="SELECT d from Dealer d WHERE d.groups.groupId = ?1")
//	List<Dealer> findGroupId(long groupId);

//	List<Dealer> findByDealerId();

}
