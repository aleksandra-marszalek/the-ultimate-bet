package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.Wallet;
import pl.coderslab.theultimatebet.repository.OperationRepository;

import java.util.List;

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

    @Override
    public List<Operation> findAllByWalletLast(Wallet wallet) {
        List<Operation> last10 = operationRepository.findAllByWalletOrderByCreatedDesc(wallet);
        last10 = last10.subList(0,3);
        return last10;
    }


}
