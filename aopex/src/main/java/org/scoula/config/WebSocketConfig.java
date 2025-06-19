package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket과 STOMP 프로토콜 설정 클래스
 *
 * 역할:
 * 1. WebSocket 연결 엔드포인트 정의
 * 2. 메시지 브로커 설정 (메시지 라우팅 규칙
 * 3. STOMP 프로토콜 활성화
 */
/*
* > 구조 및 흐름
>
1.클라이언트 연결 → WebSocket 엔드포인트 -> ws://localhost:8080/chat-app (소켓연결)
2.토픽 구독 → 메시지 수신 경로 설정 -> "/topic/?"
3.메시지 발행 → 클라이언트가 서버로 메시지 전송 -> "/app/?"
4.서버 처리 → 비즈니스 로직 실행 -> Controller @MessageMapping("/?")이 붙은 핸들러 메소드 실행
5.브로드캐스트 → 서버가 구독자들에게 메시지 전송 @SendTo("/topic/?") ?을 구독한 모든 구독자에게 전송
* */
@Configuration
@EnableWebSocketMessageBroker  // WebSocket 메시지 브로커 기능 활성화 (STOMP 프로토콜 사용)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메시지 브로커 설정
     * - 클라이언트와 서버 간 메시지 라우팅 규칙을 정의
     * - STOMP 메시지의 목적지(destination) 경로 규칙 설정
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // 1. 메모리 기반 Simple Message Broker 활성화
        config.enableSimpleBroker("/topic");


        // 2. 애플리케이션 destination prefix 설정
        config.setApplicationDestinationPrefixes("/app");
    }


    /**
     * STOMP 엔드포인트 등록
     * - 클라이언트가 WebSocket 연결을 시작할 때 사용하는 URL 정의
     * - HTTP 핸드셰이크 후 WebSocket 프로토콜로 업그레이드
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // WebSocket 연결 엔드포인트 등록
        registry.addEndpoint("/chat-app")  // 연결 URL: ws://localhost:8080/chat-app
                .setAllowedOrigins("*");   // CORS 정책: 모든 오리진 허용

        // *보안 주의: 운영환경에서는 구체적인 도메인 지정 권장
        // .setAllowedOrigins("https://yourdomain.com", "https://anotherdomain.com");

        // *SockJS 지원 (WebSocket을 지원하지 않는 브라우저 대응)
        // .withSockJS(); // 필요시 추가
    }
}

