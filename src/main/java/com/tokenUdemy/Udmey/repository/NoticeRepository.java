package com.tokenUdemy.Udmey.repository;

import java.util.List;

import com.tokenUdemy.Udmey.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    List<Notice> findAllActiveNotices();

//	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt",nativeQuery = true)
//	List<Notice> findAllActiveNotices();

}
