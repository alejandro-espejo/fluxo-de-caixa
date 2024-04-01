package com.caixa.fluxoDeCaixa.models;

import com.caixa.fluxoDeCaixa.enums.Status;
import com.caixa.fluxoDeCaixa.enums.converters.StatusConverter;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CAIXAS")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "TIPO", length = 200)
    private String tipo;

    @Column(name = "VALOR")
    private float valor;

    @Column(name = "STATUS")
    @Convert(converter = StatusConverter.class)
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caixa caixa = (Caixa) o;
        return id == caixa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
