package pl.coderslab.theultimatebet.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.service.TeamService;

public class TeamConverter implements Converter<String, Team> {

    @Autowired
    TeamService teamService;

    @Override
    public Team convert(String s) {
        return teamService.findByApiId(Long.parseLong(s));
    }
}
