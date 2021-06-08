package ru.atmsoft.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "fullname", length = 50, nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

}
