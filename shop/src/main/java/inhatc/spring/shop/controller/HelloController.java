package inhatc.spring.shop.controller;


import inhatc.spring.shop.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class HelloController {

    @GetMapping("/hello") // http://localhost/hello
    public String hello() {
        return "hello";
    }

    @GetMapping("/user") // http://localhost/user
    public UserDto user() {
        UserDto userDto = new UserDto(20, "홍길동");
        System.out.println(userDto);

//        UserDto userDto2 = new UserDto().builder().age(20).name("홍길동").build();

        return userDto;
    }

}
