package ua.socialnetwork.service;

import ua.socialnetwork.entity.Account;

public interface AccountService {
    Account create(Account account);
    Account update(Account account);
    void delete(int id);
    Account readById(int id);

}
