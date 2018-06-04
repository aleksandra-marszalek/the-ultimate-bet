package pl.coderslab.theultimatebet.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.service.GameService;
import pl.coderslab.theultimatebet.service.GroupService;

public class GameConverter implements Converter<String, Game> {

    @Autowired
    GameService gameService;

    @Override
    public Game convert(String s) {
        return gameService.findById(Long.parseLong(s));
    }
}
