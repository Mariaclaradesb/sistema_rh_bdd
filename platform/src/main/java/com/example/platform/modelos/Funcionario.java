package com.example.platform.modelos;

import java.math.BigDecimal;

public class Funcionario {
    private Long matricula;
    private String nomeCompleto;
    private ECargo cargo;
    private BigDecimal salarioBase;

    public Funcionario(Long matricula, String nomeCompleto, String cargo, BigDecimal salarioBase) {
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
        this.cargo = ECargo.valueOf(cargo);
        setSalarioBase(salarioBase);
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public ECargo getCargo() {
        return cargo;
    }

    public void setCargo(ECargo cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        if (salarioBase == null || salarioBase.signum() <= 0) {
            throw new IllegalArgumentException("O salário deve ser maior que zero");
        }
        this.salarioBase = salarioBase;
    }
}
