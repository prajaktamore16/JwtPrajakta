package com.tokenUdemy.Udmey.repository;

import com.tokenUdemy.Udmey.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Accounts findByCustomerId(int customerId);

}
