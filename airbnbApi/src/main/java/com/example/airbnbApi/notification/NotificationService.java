package com.example.airbnbApi.notification;

import com.example.airbnbApi.notification.dto.ResponseNotificationDTO;
import com.example.airbnbApi.user.Account;
import com.example.airbnbApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;


    public List<ResponseNotificationDTO> getNotificationByAccountId(Integer accountId) {

        return notificationRepository.findNotificationByAccountId(accountId);
    }
}
