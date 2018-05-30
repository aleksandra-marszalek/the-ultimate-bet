package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.UserService;

@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello () {
        return "Hello world";
    }

    @GetMapping("/helloUser")
    @ResponseBody
    public String helloUser () {
        return "Hello authenticated user";
    }

    @ResponseBody
    @GetMapping("/addUser")
    public String addUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user123");
        user.setEnabled(1);
        userService.saveUser(user);
        return "added User";
    }
}