package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.repository.BetRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class responsible for the logic related to {@link Bet}.
 * Consists of standard crud methods, specific searching methods and one scheduled method to update the bet results every 5000ms.
 */
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


    //////////// crud /////////////

    @Override
    public Bet findById(Long id) {
        return betRepository.findBetById(id);
    }

    @Override
    public List<Bet> findAllByUserId(Long id) {
        return betRepository.findAllByUserIdOrderByCreatedDesc(id);
    }

    @Override
    public void save(Bet bet) {
        betRepository.save(bet);
    }

    @Override
    public List<Bet> findAllByUserIdAndResult (Long id, String result) {
        return betRepository.findAllByUserIdAndResultOrderByCreatedDesc(id, result);
    }

    @Override
    public void delete(Bet bet) {
        betRepository.delete(bet);
    }



    ///////////// scheduled /////////////

    /**
     * Scheduled method to verify the results of the {@link Bet}.
     * On fixed rate connects to DB and checks if there are any new bets. For each bet without a result
     * checks if the game they are related to is over.
     * If the game game is over, verifies the result and give the bet status "won" or "lost".
     * If bet is won, there is new operation added to the DB and to the wallet is being transferred the amount won.
     * If bet is lost, there is no operation, but the status is set.
     * In both cases the status makes the bets inactive.
     */
    @Scheduled(fixedRate = 5000)
    public void updateBet() {
        List<Bet> bets = betRepository.findAllByResultOrderByCreatedDesc(null);
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
