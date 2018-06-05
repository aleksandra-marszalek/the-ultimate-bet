package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.UserService;

@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        try {
            Long id = customUser.getUser().getId();
            model.addAttribute("id", id);
            if (customUser.getUser().getId()!=null) {
                return "home";
            }
        } catch (Exception e) {
            return "home";
        }
        return "home";
    }

    @PostMapping("/logout")
    public String homeLogout () {return "home";}

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
        userService.saveUser(user);
        return "added User";
    }

    @ResponseBody
    @GetMapping("/addAdmin")
    public String addAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123");
        userService.saveUser(user);
        return "added admin";
    }
}
