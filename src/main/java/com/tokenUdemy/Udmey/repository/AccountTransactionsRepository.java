package com.tokenUdemy.Udmey.repository;

import java.util.List;

import com.tokenUdemy.Udmey.model.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, Long> {
	
	List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
