package steps;

import com.example.platform.modelos.ECargo;
import com.example.platform.modelos.FolhaDePagamento;
import com.example.platform.modelos.Funcionario;
import com.example.platform.modelos.Pagamento;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoliumTestePassos {
    private FolhaDePagamento folhaDePagamento;

    // Campos dos novos passos
    private List<Map<String, String>> dadosDasFolhas;
    private Pagamento pagamentoAtual;
    private BigDecimal totalSalariosBase;
    private BigDecimal totalSalariosLiquidos;

    // Passos que já estavam na classe
    @Dado("que existe uma folha de pagamento de competência: {string}")
    public void criarFolhaDePagamento(String competencia) {
        folhaDePagamento = new FolhaDePagamento(competencia);
    }

    @Quando("Cadastrar os seguintes funcionários novos")
    public void cadastroFuncionariosNovos(DataTable dataTable) {
        var funcionarios = dataTable.asMaps(String.class, String.class);
        for (var f : funcionarios) {
            var matricula = Long.parseLong(f.get("matricula"));
        }
    }

    @Entao("{int} funcionarios devem estar cadastrados com os dados informados")
    public void funcionariosCadastradosComOsDadosInformados(DataTable dataTable) {

    }

    @Quando("tentar cadastrar o funcionário {string} com matrícula {string}, cargo {string} e salário base {int}")
    public void tentoCadastrarComSalarioMenorIgualAZero(
            String nomeCompleto,
            String matricula,
            String cargo,
            int salarioBase
    ) {

    }

    @Entao("o cadastro deve ser recusado")
    public void cadastroRecusado() {

    }

    @E("a mensagem deve ser {string}")
    public void aMensagemDeveSer(String erro) {

    }

    // Novos passos: folha de pagamento e desconto por falta
    @Dado("^que existe uma folha de pagamento de competência: (\\d{4}-\\d{2})$")
    public void criarFolhaDePagamentoSemAspas(String competencia) {
        criarFolhaDePagamento(competencia);
        folhaDePagamento.setPagamentos(new ArrayList<>());
    }

    @Dado("que existem as seguintes folhas de pagamento")
    public void existemAsSeguintesFolhas(DataTable dataTable) {
        dadosDasFolhas = dataTable.asMaps(String.class, String.class);
        List<Pagamento> pagamentos = new ArrayList<>();

        for (Map<String, String> linha : dadosDasFolhas) {
            pagamentos.add(criarPagamento(
                    linha.get("matrícula"),
                    linha.get("nome"),
                    linha.get("cargo"),
                    Integer.parseInt(linha.get("salario base")),
                    Long.parseLong(linha.get("faltas"))
            ));
        }

        folhaDePagamento.setPagamentos(pagamentos);
    }

    @Quando("calculo os totais da folha")
    public void calculoOsTotaisDaFolha() {
        totalSalariosBase = folhaDePagamento.calcularTotalSalariosBase();
        totalSalariosLiquidos = folhaDePagamento.calcularTotalSalariosLiquidos();
    }

    @Entao("{int} funcionários devem estar cadastrados com os dados informados")
    public void conferirFuncionariosDasFolhas(int quantidade) {
        List<Pagamento> pagamentos = folhaDePagamento.getPagamentos();

        assertEquals(quantidade, pagamentos.size());
        assertEquals(dadosDasFolhas.size(), pagamentos.size());

        for (int i = 0; i < dadosDasFolhas.size(); i++) {
            Map<String, String> esperado = dadosDasFolhas.get(i);
            Pagamento pagamento = pagamentos.get(i);
            Funcionario funcionario = pagamento.getFuncionario();

            assertEquals(
                    Long.valueOf(esperado.get("matrícula")),
                    funcionario.getMatricula()
            );
            assertEquals(
                    esperado.get("nome"),
                    funcionario.getNomeCompleto()
            );
            assertEquals(
                    ECargo.valueOf(esperado.get("cargo")),
                    funcionario.getCargo()
            );
            compararValor(
                    Integer.parseInt(esperado.get("salario base")),
                    funcionario.getSalarioBase()
            );
            assertEquals(
                    Long.valueOf(esperado.get("faltas")),
                    pagamento.getFaltas()
            );
        }
    }

    @E("o total dos salários base deve ser {int}")
    public void totalSalariosBaseDeveSer(int esperado) {
        compararValor(esperado, totalSalariosBase);
    }

    @E("o total líquido da folha deve ser {int}")
    public void totalLiquidoDaFolhaDeveSer(int esperado) {
        compararValor(esperado, totalSalariosLiquidos);
    }

    @Quando("cadastrar o funcionário {string} com matrícula {string}, cargo {string}, salário base {int} e {int} faltas")
    public void cadastrarFuncionarioParaCalculo(
            String nome,
            String matricula,
            String cargo,
            int salarioBase,
            int faltas
    ) {
        pagamentoAtual = criarPagamento(
                matricula,
                nome,
                cargo,
                salarioBase,
                faltas
        );

        if (folhaDePagamento.getPagamentos() == null) {
            folhaDePagamento.setPagamentos(new ArrayList<>());
        }

        folhaDePagamento.getPagamentos().add(pagamentoAtual);
    }

    @Entao("o desconto por faixa salarial deve ser {int}")
    public void descontoPorFaixaSalarialDeveSer(int esperado) {
        compararValor(
                esperado,
                pagamentoAtual.calcularImpostoDeRenda()
        );
    }

    @Entao("o desconto por faltas deve ser {int}")
    public void descontoPorFaltasDeveSer(int esperado) {
        BigDecimal salarioBase =
                pagamentoAtual.getFuncionario().getSalarioBase();

        BigDecimal desconto = salarioBase
                .subtract(pagamentoAtual.calcularImpostoDeRenda())
                .subtract(pagamentoAtual.calcularSalarioLiquido());

        compararValor(esperado, desconto);
    }

    @E("o salário líquido deve ser {int}")
    public void salarioLiquidoDeveSer(int esperado) {
        compararValor(
                esperado,
                pagamentoAtual.calcularSalarioLiquido()
        );
    }

    private Pagamento criarPagamento(
            String matricula,
            String nome,
            String cargo,
            int salarioBase,
            long faltas
    ) {
        Funcionario funcionario = new Funcionario(
                Long.parseLong(matricula),
                nome,
                cargo,
                BigDecimal.valueOf(salarioBase)
        );

        Pagamento pagamento = new Pagamento();
        pagamento.setFuncionario(funcionario);
        pagamento.setFaltas(faltas);

        return pagamento;
    }

    private void compararValor(int esperado, BigDecimal atual) {
        assertEquals(
                0,
                BigDecimal.valueOf(esperado).compareTo(atual)
        );
    }
}