package pl.coderslab.theultimatebet.service;

import pl.coderslab.theultimatebet.entity.Operation;
import pl.coderslab.theultimatebet.entity.Wallet;

import java.util.List;

public interface OperationService {

    public void save (Operation operation);

    public List<Operation> findAll ();

    public List<Operation> findAllByWalletLast(Wallet wallet);
}
