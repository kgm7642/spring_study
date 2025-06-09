package org.lecture.after;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 여러 빈 중 우선 주입
public class SMSSender extends EmailSender {

    // 문자보내는 기능이라고 가정
    @Override
    public void send(String message) {
        System.out.println("SMS 발송 : " + message);
    }
}
