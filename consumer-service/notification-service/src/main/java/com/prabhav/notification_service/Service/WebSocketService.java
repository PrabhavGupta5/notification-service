package com.prabhav.notification_service.Service;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(Long userId, String message) {
        System.out.println("Pushing notification to user: " + userId);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + userId,
                message
        );
    }
}