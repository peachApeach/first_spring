package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody  // http 통신의 body 부분
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // ctrl + shift + enter를 치면 문장 끝이 ;로 완성된다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //객체부터 생성
    static class Hello {
        private String name;
        // ctrl + n
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
