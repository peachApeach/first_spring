package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController
{
    // 웹 어플리케이션에서 /hello라고 경로를 지정할 경우 해당 메서드를 실행한다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!!");
        return "hello";
    }

    // MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    // RequestParam은 외부에서 변수를 받기 위한 함수
    public String hellomvc(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
