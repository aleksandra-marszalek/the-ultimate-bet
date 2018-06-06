package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.entity.Wallet;

public interface FavouriteService {


    public Favourite findFavouriteByUser (User user);

    void save(Favourite favourite);


}
