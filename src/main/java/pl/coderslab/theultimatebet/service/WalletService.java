package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;

public interface WalletService {

    public Wallet findWalletByUser(User user);
}
