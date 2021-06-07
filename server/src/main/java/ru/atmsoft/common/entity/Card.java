package ru.atmsoft.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cardnum", length = 16, unique = true, nullable = false)
    private String cardNum;

    @Column(name = "pin", length = 4, nullable = false)
    private String pin;

    @Column(name = "validity", nullable = false)
    private LocalDate validity;

    @Column(name = "attemptscount", nullable = false)
    private Integer attemptsCount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Account> accounts;

}
