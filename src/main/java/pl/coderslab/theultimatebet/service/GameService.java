package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.entity.User;

import java.util.List;

public interface GameService {

    public Game findByApiId (Long id);

    public Game findById (Long id);

    public List<Game> findAllByStatus (int status);

    public List<Game> findGamesByUserFavourite (User user);

    public List<Game> findSuggestedGames ();
}
