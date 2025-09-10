package com.betacom.com.models;

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

    @Column(name = "carrello", columnDefinition = "integer[]")
    private Integer[] carrello;

}
