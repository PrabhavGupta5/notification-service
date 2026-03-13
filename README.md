## Overview

	Notification Service consumes user events from Kafka and processes them to generate notifications.
	
	It stores notifications in PostgreSQL and maintains unread notification counters using Redis.
	
	This service acts as the event consumer in the event-driven system.

## Tech Stack
	•	Java 17
	•	Spring Boot
	•	Apache Kafka
	•	PostgreSQL
	•	Redis
	•	WebSockets
	•	Docker

## System Architecture
	
	Event Service → Kafka → Notification Service → WebSocket → Client
	
	Flow:
		1.	Event Service publishes user events to Kafka.
		2.	Notification Service consumes the event.
		3.	Notification is stored in PostgreSQL.
		4.	Redis updates unread notification counter.
		5.	WebSocket pushes notification instantly to connected clients.

## Project Structure: 
		notification-service
		│
		├── consumer
		│   └── NotificationConsumer
		│
		├── service
		│   └── NotificationService
		|   └── RedisService
		|   └── WebSocketService
		│
		├── Consumer
		│   └── NotificationEventConsumer
		│
		├── entity
		│   └── Notification
		│
		├── repository
		│   └── NotificationRepository
		│
		├── config
		│   └── WebSocketConfig
		│
		└── NotificationServiceApplication


## Future Improvements
	•	Notification preferences
	•	Email / SMS notifications
	•	Dead Letter Queue for failed Kafka events
	•	Rate limiting
	•	Notification batching

## Database Image: 
<img width="1512" height="930" alt="Screenshot 2026-03-13 at 8 52 58 PM" src="https://github.com/user-attachments/assets/dfdfede6-3171-422e-ab12-e2b1a7f49fae" />

