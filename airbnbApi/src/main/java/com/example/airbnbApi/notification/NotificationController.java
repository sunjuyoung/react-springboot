package com.example.airbnbApi.notification;

import com.example.airbnbApi.notification.dto.ResponseNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{account_id}")
    public ResponseEntity<?> getNotificationByAccountId(@PathVariable("account_id")Integer accountId){

        List<ResponseNotificationDTO> dtos = notificationService.getNotificationByAccountId(accountId);

        return ResponseEntity.ok().body("");
    }
}
