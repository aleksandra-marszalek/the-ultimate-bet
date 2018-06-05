package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.repository.BetRepository;
import pl.coderslab.theultimatebet.repository.GameRepository;
import pl.coderslab.theultimatebet.repository.WalletRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BetServiceImpl implements BetService {

    @Autowired
    BetRepository betRepository;

    @Autowired
    GameService gameService;

    @Autowired
    OperationService operationService;

    @Autowired
    WalletService walletService;

    @Override
    public Bet findById(Long id) {
        return betRepository.findBetById(id);
    }

    @Override
    public List<Bet> findAllByUserId(Long id) {
        return betRepository.findAllByUserId(id);
    }

    @Override
    public void save(Bet bet) {
        betRepository.save(bet);
    }

    @Scheduled(fixedRate = 5000)
    public void updateBet() {
        List<Bet> bets = betRepository.findAllByResult(null);
        for (Bet bet: bets) {
            Game game = bet.getGame();
            Wallet wallet = bet.getUser().getWallet();
            if (game.getStatus() == 1) {
                Team userTeam = bet.getTeam();
                if (game.getPointsTeam1() > game.getPointsTeam2() && userTeam.equals(game.getTeam1())) {
                    wallet.setBalance(wallet.getBalance().add(bet.getTotalAmount()));
                    bet.setResult("won");
                    Operation operation = new Operation();
                    operation.setCreated(LocalDateTime.now());
                    operation.setAmount(bet.getTotalAmount());
                    operation.setWallet(wallet);
                    operation.setTitle("won bet nr " + bet.getId());
                    operationService.save(operation);
                    walletService.save(wallet);
                } else {
                    bet.setResult("lost");
                }
                betRepository.save(bet);

            }
        }

    }



}
