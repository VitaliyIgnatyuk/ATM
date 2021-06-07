package ru.atmsoft.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.atmsoft.common.entity.Client;

import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

}
