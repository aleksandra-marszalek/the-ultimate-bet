package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {


}
