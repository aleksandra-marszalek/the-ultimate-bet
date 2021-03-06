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

/**
 * Controller responsible for providing all the data related to {@link User} and connecting the services with the views.
 */
@Controller
public class userController {

    @Autowired
    UserService userService;

    /**
     * GET for adding a new {@link User}
     * @param model to provide the new User to form
     * @return the form of registration
     */
    @GetMapping("/newUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * POST for adding a new {@link User}
     * @param user which keeps all the crucial data of {@link User}
     * @param result BindingResult
     * @param model to provide the to form if errors
     * @return registration if errors and redirect to home if user successfully added to Db
     */
    @PostMapping("/newUser")
    public String newUser(@Validated({ValidationUser.class}) @ModelAttribute User user, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "registration";
            }
            if (userService.checkUsername(user)){
            model.addAttribute("info", "This username already exists");
            return "registration";
            }
        if (userService.checkEmail(user)){
            model.addAttribute("info2", "This email already exists");
            return "registration";
        }
        if (!user.isAdult()) {
            model.addAttribute("info3", "You must be at least 18 to sign up.");
            return "registration";
        }
            userService.saveUser(user);
            return "redirect:/";
    }

}
