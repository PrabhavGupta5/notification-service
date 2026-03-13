package com.prabhav.notification_service.controller;

import com.prabhav.notification_service.Service.RedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final RedisService redisService;

    public NotificationController (RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/unread-count")
    public String getUnreadNotificationCount(@RequestParam Long userId) {
        String unreadCount = redisService.getUnreadCount(userId);
        return unreadCount != null ? unreadCount : "0";
    }

    @PostMapping("/mark-read/{userId}")
    public void markNotificationsRead(@PathVariable Long userId) {
        redisService.resetUnreadCount(userId);
    }
}
