package com.tokenUdemy.Udmey.repository;

import com.tokenUdemy.Udmey.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	
}
