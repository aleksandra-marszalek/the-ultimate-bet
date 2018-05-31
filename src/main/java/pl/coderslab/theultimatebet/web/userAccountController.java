package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;
import pl.coderslab.theultimatebet.service.OperationService;
import pl.coderslab.theultimatebet.service.UserService;
import pl.coderslab.theultimatebet.service.WalletService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userAccountController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    OperationService operationService;

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
            List<Operation> lastOps = operationService.findAllByWalletLast(wallet);
            model.addAttribute("operations", lastOps);
            model.addAttribute("id", id);
            return "UserWallet";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/wallet/addMoney/{amount}")
    public String addMoney (@PathVariable Long id, @PathVariable Integer amount, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser.getUser().getId() == id) {
            model.addAttribute("amount", amount);
            model.addAttribute("id", id);
            return "addMoney";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{id}/wallet/addMoney/{amount}")
    public String addMoney (@PathVariable Long id, @PathVariable Integer amount, @RequestParam String agree) {
        if (agree.equals("yes")) {
            User user = userService.findById(id);
            Wallet wallet = walletService.findWalletByUser(user);
            BigDecimal amountDecimal = BigDecimal.valueOf(amount);
            wallet.setBalance(wallet.getBalance().add(amountDecimal));
            walletService.save(wallet);

            Operation operation = new Operation();
            operation.setCreated(LocalDateTime.now());
            operation.setAmount(amountDecimal);
            operation.setWallet(wallet);
            operation.setTitle("add money");
            operationService.save(operation);
        }
        return "redirect:/user/"+id+"/wallet";
    }

}
