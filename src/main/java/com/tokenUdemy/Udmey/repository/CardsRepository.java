package com.tokenUdemy.Udmey.repository;

import java.util.List;

import com.tokenUdemy.Udmey.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}
