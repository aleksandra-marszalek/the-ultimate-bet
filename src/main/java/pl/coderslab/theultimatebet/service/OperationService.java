package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Operation;

import java.util.List;

public interface OperationService {

    public void save (Operation operation);

    public List<Operation> findAll ();
}
