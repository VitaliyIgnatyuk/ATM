package ru.atmsoft.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.atmsoft.common.entity.Account;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {
}
