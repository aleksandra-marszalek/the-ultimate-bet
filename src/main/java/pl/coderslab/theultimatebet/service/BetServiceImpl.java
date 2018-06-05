package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Bet;
import pl.coderslab.theultimatebet.repository.BetRepository;

import java.util.List;


@Service
public class BetServiceImpl implements BetService {

    @Autowired
    BetRepository betRepository;


    @Override
    public Bet findById(Long id) {
        return betRepository.findBetById(id);
    }

    @Override
    public List<Bet> findAllByUserId(Long id) {
        return betRepository.findAllByUserId(id);
    }


}
