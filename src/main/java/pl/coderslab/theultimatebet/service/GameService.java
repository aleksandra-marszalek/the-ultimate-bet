package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Group;

import java.util.List;

public interface GameService {

    public Game findById (Long id);

    public List<Game> findAllByStatus (int status);


}
