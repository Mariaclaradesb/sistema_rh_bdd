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

    public FolhaDePagamento(String competencia) {
        this.competencia = competencia;
    }

    public BigDecimal calcularTotalSalariosLiquidos() {
        BigDecimal soma = BigDecimal.ZERO;

        for (var pagamento : pagamentos) {
            soma = soma.add(pagamento.calcularSalarioLiquido());
        }

        return soma;
    }

    public BigDecimal calcularTotalSalariosBase() {
        BigDecimal soma = BigDecimal.ZERO;

        for (var pagamento : pagamentos) {
            soma = soma.add(pagamento
                    .getFuncionario()
                    .getSalarioBase()
            );
        }

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
