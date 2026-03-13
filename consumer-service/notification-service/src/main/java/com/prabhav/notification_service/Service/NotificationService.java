package com.prabhav.notification_service.Service;

import com.prabhav.event_service.Entity.UserEvent;
import com.prabhav.notification_service.entity.Notification;
import com.prabhav.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void processNotification(UserEvent event) {
        System.out.println("Notification received!");

        System.out.println("User Sender Id: " + event.getSenderId());
        System.out.println("User Receiver Id: " + event.getReceiverId());
        System.out.println("Message: " + event.getMessage());
        System.out.println("EventType: " + event.getEventType());
    }

    public void saveNotification(Long userId, Long fromUserId,  String eventType) {

        Notification notification = new Notification();
        notification.setSenderId(userId);
        notification.setReceiverId(fromUserId); // Set fromUserId if needed
        notification.setEventType(eventType);
        notification.setMessage("User event received: " + eventType);

        notificationRepository.save(notification);
    }


}