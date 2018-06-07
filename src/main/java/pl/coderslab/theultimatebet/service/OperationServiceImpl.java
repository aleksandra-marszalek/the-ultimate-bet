package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.Wallet;
import pl.coderslab.theultimatebet.repository.OperationRepository;

import java.util.List;

/**
 * Service class containing mainly the methods to manage the operations.
 */
@Service
public class OperationServiceImpl implements OperationService {


    @Autowired
    OperationRepository operationRepository;

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    /**
     * Method used to find the last 10 operations in the wallet.
     * @param wallet is {@link Wallet}
     * @return last 10 operations in wallet, ordered by created, desc
     */
    @Override
    public List<Operation> findAllByWalletLast(Wallet wallet) {
        List<Operation> last10 = operationRepository.findAllByWalletOrderByCreatedDesc(wallet);
        if (last10.size()>=10) {
            last10 = last10.subList(0,10);
        }
        return last10;
    }


}
