package com.caixa.fluxoDeCaixa.enums;

public enum Status {
    RECEITA(1), DESPESA(2);

    private int valor;

    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public static Status fromCode(Integer value) {
        for (Status type : Status.values()) {
            if (type.getValor() == value) {
                return type;
            }
        }
        return null;
    }
}
