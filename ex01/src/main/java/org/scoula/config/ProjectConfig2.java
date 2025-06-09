package org.scoula.config;

import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정파일로 스프링 프레임워크에 알려주는 역할
public class ProjectConfig2 {

    // 설정값이 다른 동일한 클래스의 객체를 싱글톤으로 여러개 만들어야하는 경우
    @Bean(name="KoKo")
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("KoKo");
        return p;
    }

    @Bean(name="Miki")
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }

    @Bean
    public Parrot parrot3() {
        Parrot p = new Parrot();
        p.setName("Riki");
        return p;
    }

    // 설정이 다른 Parrot객체를 여러개 만들어야하는 경우
    // 기본으로는 메서드이름이 객체이름이 됨.
    // Parrot parrot1 = new Parrot();
    // 별도로 설정도 가능함. @Bean(name="miki")
}
