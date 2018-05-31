package pl.coderslab.theultimatebet.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.theultimatebet.CurrentUser;

@Controller
@RequestMapping("/user")
public class userAccountController {

    @GetMapping("/{id}")
    public String userAccount (@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser.getUser().getId() == id) {
            model.addAttribute("id", id);
            return "UserAccount";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/{id}/wallet")
    public String showWallet () {
        return null;
    }
}
