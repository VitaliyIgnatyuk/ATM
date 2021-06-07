package ru.atmsoft.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.atmsoft.common.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "accountnum", length = 20, unique = true, nullable = false)
    private String accountNum;

    @Column(name = "currency", nullable = false)
    private Integer currency;

    private BigDecimal amount;

    @Column(name = "main", nullable = false)
    private Boolean main;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Currency getCurrency() {
        return Currency.getType(this.currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency == null ? null : currency.getCodeISO();
    }

}
