package com.tccAppBancoDeSangue.BloodLink.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tccAppBancoDeSangue.BloodLink.dto.NotificationDTO;
import com.tccAppBancoDeSangue.BloodLink.service.FirebaseService;


@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final FirebaseService firebaseService;

    NotificationController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/teste")
    public ResponseEntity<Void> sendTestNotification(@RequestBody NotificationDTO dto) {
        try {
            // Aqui você pode definir o título, corpo e token de teste
            String title = dto.title();
            String body = dto.body();
            String token = dto.token();

            firebaseService.sendNotification(title, body, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
