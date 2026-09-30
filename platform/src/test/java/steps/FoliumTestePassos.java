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

import static org.junit.jupiter.api.Assertions.*;

public class FoliumTestePassos {

    private FolhaDePagamento folhaDePagamento;
    private List<Map<String, String>> dadosInformados;
    private Pagamento pagamentoAtual;
    private BigDecimal totalSalariosBase;
    private BigDecimal totalSalariosLiquidos;
    private IllegalArgumentException erro;


    @Dado("^que existe uma folha de pagamento de competência: (\\d{4}-\\d{2})$")
    public void criarFolhaDePagamento(String competencia) {
        folhaDePagamento = new FolhaDePagamento(competencia);
        folhaDePagamento.setPagamentos(new ArrayList<>());

        dadosInformados = null;
        pagamentoAtual = null;
        totalSalariosBase = null;
        totalSalariosLiquidos = null;
        erro = null;
    }

    @Quando("cadastrar os seguintes funcionários novos")
    public void cadastroFuncionariosNovos(DataTable dataTable) {
        cadastrarFuncionariosDaTabela(dataTable);
    }

    @Dado("que existem as seguintes folhas de pagamento")
    public void existemAsSeguintesFolhasDePagamento(DataTable dataTable) {
        cadastrarFuncionariosDaTabela(dataTable);
    }

    private void cadastrarFuncionariosDaTabela(DataTable dataTable) {
        dadosInformados = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> linha : dadosInformados) {
            long faltas = linha.containsKey("faltas")
                    ? Long.parseLong(linha.get("faltas"))
                    : 0L;

            adicionarPagamento(
                    linha.get("matrícula"),
                    linha.get("nome"),
                    linha.get("cargo"),
                    new BigDecimal(linha.get("salario base")),
                    faltas
            );
        }
    }

    @Entao("{int} funcionários devem estar cadastrados com os dados informados")
    public void funcionariosCadastradosComOsDadosInformados(int quantidade) {
        assertNotNull(dadosInformados);
        assertEquals(quantidade, dadosInformados.size());
        assertEquals(quantidade, folhaDePagamento.getPagamentos().size());

        for (int i = 0; i < dadosInformados.size(); i++) {
            Map<String, String> esperado = dadosInformados.get(i);
            Pagamento pagamento = folhaDePagamento.getPagamentos().get(i);
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
                    new BigDecimal(esperado.get("salario base")),
                    funcionario.getSalarioBase()
            );

            if (esperado.containsKey("faltas")) {
                assertEquals(
                        Long.valueOf(esperado.get("faltas")),
                        pagamento.getFaltas()
                );
            }
        }
    }

    @Quando("tentar cadastrar o funcionário {string} com matrícula {string}, cargo {string} e salário base {int}")
    public void tentoCadastrarComSalarioMenorIgualAZero(
            String nomeCompleto,
            String matricula,
            String cargo,
            int salarioBase
    ) {
        try {
            adicionarPagamento(
                    matricula,
                    nomeCompleto,
                    cargo,
                    BigDecimal.valueOf(salarioBase),
                    0L
            );
        } catch (IllegalArgumentException excecao) {
            erro = excecao;
        }
    }

    @Entao("o cadastro deve ser recusado")
    public void cadastroRecusado() {
        assertNotNull(erro);
        assertTrue(folhaDePagamento.getPagamentos().isEmpty());
    }

    @E("a mensagem deve ser {string}")
    public void aMensagemDeveSer(String mensagemEsperada) {
        assertNotNull(erro);
        assertEquals(mensagemEsperada, erro.getMessage());
    }

    // FOLHAS DE PAGAMENTO

    @Quando("calculo os totais da folha")
    public void calculoOsTotaisDaFolha() {
        totalSalariosBase = folhaDePagamento.calcularTotalSalariosBase();
        totalSalariosLiquidos =
                folhaDePagamento.calcularTotalSalariosLiquidos();
    }

    @E("o total dos salários base deve ser {int}")
    public void totalDosSalariosBaseDeveSer(int esperado) {
        compararValor(
                BigDecimal.valueOf(esperado),
                totalSalariosBase
        );
    }

    @E("o total líquido da folha deve ser {int}")
    public void totalLiquidoDaFolhaDeveSer(int esperado) {
        compararValor(
                BigDecimal.valueOf(esperado),
                totalSalariosLiquidos
        );
    }

    @Quando("cadastrar o funcionário {string} com matrícula {string}, cargo {string}, salário base {int} e {int} faltas")
    public void cadastrarFuncionarioParaCalculo(
            String nome,
            String matricula,
            String cargo,
            int salarioBase,
            int faltas
    ) {
        pagamentoAtual = adicionarPagamento(
                matricula,
                nome,
                cargo,
                BigDecimal.valueOf(salarioBase),
                faltas
        );
    }

    @Entao("o desconto por faixa salarial deve ser {int}")
    public void descontoPorFaixaSalarialDeveSer(int esperado) {
        compararValor(
                BigDecimal.valueOf(esperado),
                pagamentoAtual.calcularImpostoDeRenda()
        );
    }

    @Entao("o desconto por faltas deve ser {int}")
    public void descontoPorFaltasDeveSer(int esperado) {
        BigDecimal salarioBase =
                pagamentoAtual.getFuncionario().getSalarioBase();

        BigDecimal descontoPorFaltas = salarioBase
                .subtract(pagamentoAtual.calcularImpostoDeRenda())
                .subtract(pagamentoAtual.calcularSalarioLiquido());

        compararValor(
                BigDecimal.valueOf(esperado),
                descontoPorFaltas
        );
    }

    @E("o salário líquido deve ser {int}")
    public void salarioLiquidoDeveSer(int esperado) {
        compararValor(
                BigDecimal.valueOf(esperado),
                pagamentoAtual.calcularSalarioLiquido()
        );
    }

    private Pagamento adicionarPagamento(
            String matricula,
            String nome,
            String cargo,
            BigDecimal salarioBase,
            long faltas
    ) {
        Funcionario funcionario = new Funcionario(
                Long.parseLong(matricula),
                nome,
                cargo,
                salarioBase
        );

        Pagamento pagamento = new Pagamento();
        pagamento.setFuncionario(funcionario);
        pagamento.setFaltas(faltas);

        folhaDePagamento.getPagamentos().add(pagamento);
        return pagamento;
    }

    private void compararValor(BigDecimal esperado, BigDecimal atual) {
        assertNotNull(atual);
        assertEquals(0, esperado.compareTo(atual));
    }
}