package com.example.airbnbApi.notification.dto;

import com.example.airbnbApi.notification.NotificationType;
import com.example.airbnbApi.user.Account;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseNotificationDTO {

    private Integer id;

    private String  title;

    private String link;

    private String message;

    private boolean checked;

    private String name;

    private LocalDateTime createdLocalDateTime;

    private NotificationType notificationType;
}
