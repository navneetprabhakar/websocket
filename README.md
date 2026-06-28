# WebSocket Chat

A minimal real-time chat application built with Spring Boot, STOMP over WebSocket, and SockJS.

This is a small, self-contained demo: a browser client connects over WebSocket, sends messages to the server, and the server broadcasts each message (timestamped and HTML-escaped) to all connected clients via a STOMP topic.

## Features

- Real-time messaging over WebSocket using the STOMP sub-protocol
- SockJS fallback for browsers/networks without native WebSocket support
- Simple in-memory message broker (`/topic`) with an `/app` application prefix
- Server-side HTML escaping of names and messages
- Server-rendered timestamps on each broadcast message
- Static browser client (Bootstrap + jQuery + SockJS + STOMP) served by the app

## Tech Stack

- Java 8
- Spring Boot 4.1.0-M1 (`spring-boot-starter-websocket`, `spring-messaging`)
- STOMP over WebSocket with SockJS
- Lombok
- Jackson (JSON serialization)
- WebJars: Bootstrap, jQuery, SockJS, STOMP
- Maven

## Getting Started

### Prerequisites

- JDK 8+
- Maven 3.6+

### Build and Run

```bash
# Build
mvn clean package

# Run
mvn spring-boot:run
```

Then open <http://localhost:8080> in your browser, click **Connect**, enter a name and message, and **Send**.

## How It Works

- `WebSocketConfig` enables a STOMP message broker, registers the `/chat` endpoint (with SockJS fallback), exposes the simple broker on `/topic`, and sets `/app` as the application destination prefix.
- `WebSocketController` handles messages mapped to `/app/chat`, escapes the input, prepends a timestamp, and broadcasts the result to subscribers of `/topic/messages`.
- The static client in `src/main/resources/static/` connects, subscribes to `/topic/messages`, and renders incoming messages.

## Project Structure

```
websocket/
├── pom.xml
└── src/main/
    ├── java/com/navneet/websocket/
    │   ├── WebsocketApplication.java     # Spring Boot entry point
    │   ├── config/WebSocketConfig.java   # STOMP / broker configuration
    │   ├── controller/WebSocketController.java
    │   └── model/                        # MessageRequest, SocketResponse
    └── resources/
        ├── application.properties
        └── static/                       # index.html, app.js (browser client)
```

## Configuration

Default configuration lives in `src/main/resources/application.properties` (empty by default — the app runs on the standard Spring Boot port `8080`). Override as needed, for example:

```properties
server.port=<PORT>
```
