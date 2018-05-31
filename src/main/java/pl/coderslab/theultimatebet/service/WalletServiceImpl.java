package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;
import pl.coderslab.theultimatebet.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findWalletByUser(user);
    }

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }


}
