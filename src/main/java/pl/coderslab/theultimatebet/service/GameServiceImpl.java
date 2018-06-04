package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameRepository.findGameById(id);
    }
}
