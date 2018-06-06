package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.User;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    Favourite findFavouriteById (Long id);

    Favourite findFavouriteByUser (User user);
}
