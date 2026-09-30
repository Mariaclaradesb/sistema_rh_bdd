package steps;

import com.example.platform.modelos.FolhaDePagamento;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class FoliumTestePassos {
    private FolhaDePagamento folhaDePagamento;

    @Dado("que existe uma folha de pagamento de competência: {string}")
    public void criarFolhaDePagamento(String competencia) {
        folhaDePagamento = new FolhaDePagamento(competencia);
    }

    @Quando("Cadastrar os seguintes funcionários novos")
    public void cadastroFuncionariosNovos(DataTable dataTable) {
        var funcionarios = dataTable.asMap(String.class, String.class);

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
    ){

    }

    @Entao("o cadastro deve ser recusado")
    public void cadastroRecusado(){

    }

    @E("a mensagem deve ser {string}")
    public void aMensagemDeveSer(String erro){

    }
}