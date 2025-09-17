package com.betacom.com.models;

import java.util.ArrayList;
import java.util.List;

import com.betacom.com.utils.Roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "carrello_utente_oggetto", 
        joinColumns = @JoinColumn(name = "utente_id"),
        inverseJoinColumns = @JoinColumn(name = "oggetto_id")
    )
    private List<Oggetto> carrello = new ArrayList<>();
    
    private Roles role;

}
