package com.prabhav.notification_service.Consumer;

import com.prabhav.event_service.Entity.UserEvent;
import com.prabhav.notification_service.Service.NotificationService;
import com.prabhav.notification_service.Service.RedisService;
import com.prabhav.notification_service.Service.WebSocketService;
import org.slf4j.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    private final NotificationService notificationService;
    private final RedisService redisService;
    private final WebSocketService webSocketService;
    private static final Logger log = LoggerFactory.getLogger(NotificationEventConsumer.class);

    public NotificationEventConsumer(NotificationService notificationService, RedisService redisService, WebSocketService webSocketService) {
        this.notificationService = notificationService;
        this.redisService = redisService;
        this.webSocketService = webSocketService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consume(UserEvent event) {
        log.info("Event received: {}", event);

        notificationService.processNotification(event);

        // Save the notification to the database
        notificationService.saveNotification(
                event.getSenderId(),
                event.getReceiverId(),
                event.getEventType()
        );

        // Increment the unread notification count in Redis
        redisService.incrementUnreadCount(event.getReceiverId());

        webSocketService.sendNotification(
                event.getSenderId(),
                "New notification received!"
        );
    }
}