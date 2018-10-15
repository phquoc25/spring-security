package com.qph.beans;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class Company {
    private Long id;
    private String name;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
