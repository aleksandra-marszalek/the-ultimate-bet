package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    FavouriteRepository favouriteRepository;


    @Override
    public Favourite findFavouriteByUser(User user) {
        return favouriteRepository.findFavouriteByUser(user);
    }

    @Override
    public void save(Favourite favourite) {
        favouriteRepository.save(favourite);
    }
}
