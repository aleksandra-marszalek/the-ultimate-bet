package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.UserService;
import pl.coderslab.theultimatebet.validationGroups.ValidationUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class userController {

    @Autowired
    UserService userService;

    @GetMapping("/newUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/newUser")
    public String newUser(@Validated({ValidationUser.class}) @ModelAttribute User user, BindingResult result) {
            if (result.hasErrors()) {
                return "registration";
            }
            userService.saveUser(user);
            return "redirect:/";
    }

}
