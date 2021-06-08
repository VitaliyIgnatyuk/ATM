package ru.atmsoft.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.atmsoft.server.entity.Account;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {
}