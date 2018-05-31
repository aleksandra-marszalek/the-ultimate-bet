package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Operation;
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


}
