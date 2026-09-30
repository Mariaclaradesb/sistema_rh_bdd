package com.example.platform.modelos;

import java.math.BigDecimal;
import java.util.List;

public class FolhaDePagamento {
    private String competencia;
    private List<Pagamento> pagamentos;

    public FolhaDePagamento() {
    }

    public FolhaDePagamento(String competencia, List<Pagamento> pagamentos) {
        this.competencia = competencia;
        this.pagamentos = pagamentos;
    }

    public BigDecimal calcularTotalSalariosLiquidos() {
        BigDecimal soma = new BigDecimal(0);

        pagamentos.forEach(pagamento -> {
           var salario = pagamento.calcularSalarioLiquido();
           soma.add(salario);
        });

        return soma;
    }

    public BigDecimal calcularTotalSalariosBase() {
        BigDecimal soma = new BigDecimal(0);

        pagamentos.forEach(pagamento -> {
            var salarioBase = pagamento.getFuncionario()
                    .getSalarioBase();
            soma.add(salarioBase);
        });

        return soma;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
