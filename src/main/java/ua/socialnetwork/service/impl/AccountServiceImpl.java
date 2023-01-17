package ua.socialnetwork.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.socialnetwork.entity.Account;
import ua.socialnetwork.repo.AccountRepo;
import ua.socialnetwork.service.AccountService;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;

    @Override
    public Account create(Account account) {
        return accountRepo.save(account);

    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Account readById(int id) {
        return accountRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Account with id: " + id + "has not been found"));
    }
}
