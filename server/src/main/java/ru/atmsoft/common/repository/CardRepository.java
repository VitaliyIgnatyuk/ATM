package ru.atmsoft.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.atmsoft.common.entity.Card;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends CrudRepository<Card, UUID> {

    Optional<Card> findByCardNum(String cardNum);

}
