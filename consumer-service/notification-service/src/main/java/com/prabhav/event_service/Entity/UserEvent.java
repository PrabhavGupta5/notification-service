package com.prabhav.event_service.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEvent {

    private Long senderId;
    private Long receiverId;
    private String message;
    private String eventType;

}
