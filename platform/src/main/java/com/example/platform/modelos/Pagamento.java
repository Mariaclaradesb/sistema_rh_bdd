package com.example.platform.modelos;

import java.math.BigDecimal;

public class Pagamento {

    private Funcionario funcionario;
    private Long faltas;

    public BigDecimal calcularSalarioLiquido() {
        return funcionario.getSalarioBase()
                .subtract(calcularImpostoDeRenda())
                .subtract(calcularDescontoPorFaltas());
    }

    private BigDecimal calcularDescontoPorFaltas() {
        var valorFaltas = new BigDecimal(100)
                .multiply(new BigDecimal(faltas));

        return valorFaltas;
    }

    public BigDecimal calcularImpostoDeRenda() {
        var salarioBase = funcionario.getSalarioBase();

        if (salarioBase.compareTo(new BigDecimal("2428.80")) <= 0) {
            return BigDecimal.ZERO;
        } else if (salarioBase.compareTo(new BigDecimal("2826.65")) <= 0) {
            return salarioBase.multiply(new BigDecimal("0.075"));
        } else if (salarioBase.compareTo(new BigDecimal("3751.05")) <= 0) {
            return salarioBase.multiply(new BigDecimal("0.15"));
        } else if (salarioBase.compareTo(new BigDecimal("4664.68")) <= 0) {
            return salarioBase.multiply(new BigDecimal("0.225"));
        } else {
            return salarioBase.multiply(new BigDecimal("0.275"));
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getFaltas() {
        return faltas;
    }

    public void setFaltas(Long faltas) {
        this.faltas = faltas;
    }
}