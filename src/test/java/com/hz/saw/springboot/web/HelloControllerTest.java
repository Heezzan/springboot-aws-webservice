package com.hz.saw.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/*
 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴 (SpringExtension라는 실행자 사용)
 스프링 부트 테스트와 JUnit 사이에 연결자 역할
* */
@ExtendWith(SpringExtension.class)
/*
 여러 스프링 어노테이션 중 Web(Spring MVC)에 집중할 수 있는 어노테이션
 @Controller, @ControllerAdvice 등을 사용 가능, @Service, @Component, @Repository 등은 사용 불가
 여기에선 컨트롤러만 사용하기 때문에 선언
* */
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired  // 스프링이 관리하는 빈(Bean) 주입
    // 웹 API 테스트시 사용. 스프링 MVC 테스트의 시작점. 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청. 체이닝이 지원되서 여러 검증 기능 이어서 선언 가능
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", "na")
                // param의 값은 String만 허용되기 떄문에 숫자/날짜 등의 데이터도 등록 시 문자열로 변경해야됨
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
