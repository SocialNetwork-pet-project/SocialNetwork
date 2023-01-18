package ua.socialnetwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.socialnetwork.entity.Account;


public interface AccountRepo extends JpaRepository<Account, Integer> {


}
