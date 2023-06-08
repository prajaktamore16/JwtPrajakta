package com.tokenUdemy.Udmey.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tokenUdemy.Udmey.model.Notice;
import com.tokenUdemy.Udmey.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
//    public ResponseEntity<List<Notice>> getNotices() {
//        List<Notice> notices = noticeRepository.findAllActiveNotices();
//        if (notices != null ) {
//            return ResponseEntity.ok()
//                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
//                    .body(notices);
//        }else {
//            return null;
//        }
//    }


    public ResponseEntity<List<Notice>> getNoice(){
        List<Notice> notices = noticeRepository.findAll();
        return ResponseEntity.ok().body(notices);
    }

}
