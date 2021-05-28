package com.hz.saw.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
// 이 어노테이션을 통해 스프링부트의 자동 설정, 스프링 Bean 읽기, 생성을 모두 자동으로 설정
// 이 어노테이션이 있는 위치부터 설정을 읽기 때문에 항상 프로젝트의 최상단에 위치해야됨
@SpringBootApplication
public class Application {  // 앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args) {
        // 아래 명령어를 통해 내장 WAS를 실행하게 됨
        SpringApplication.run(Application.class, args);
    }
}
