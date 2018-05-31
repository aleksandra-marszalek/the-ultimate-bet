package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;
import pl.coderslab.theultimatebet.service.WalletService;

@Controller
@RequestMapping("/user")
public class userAccountController {

    @Autowired
    WalletService walletService;

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
    public String showWallet (@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser.getUser().getId() == id) {
            Wallet wallet = walletService.findWalletByUser(customUser.getUser());
            model.addAttribute("wallet", wallet);
            model.addAttribute("id", id);
            return "UserWallet";
        } else {
            return "redirect:/";
        }
    }
}
