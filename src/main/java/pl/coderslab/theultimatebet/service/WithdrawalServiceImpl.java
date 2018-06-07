package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Withdrawal;
import pl.coderslab.theultimatebet.repository.WithdrawalRepository;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {


    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Override
    public void save(Withdrawal withdrawal) {
        withdrawalRepository.save(withdrawal);
    }
}
