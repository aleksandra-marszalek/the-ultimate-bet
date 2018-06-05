package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Bet;

import java.util.List;

public interface BetService {

    public Bet findById (Long id);

    public List<Bet> findAllByUserId (Long id);

    public void save (Bet bet);
}
