package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.User;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
