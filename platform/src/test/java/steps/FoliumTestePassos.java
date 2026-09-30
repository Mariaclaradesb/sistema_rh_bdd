package steps;

import com.example.platform.modelos.Conta;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import static org.junit.jupiter.api.Assertions.*;

public class FoliumTestePassos {
    //@Dado("que não existem funcionários cadastrados ainda")
    @Quando("Cadastrar os seguintes funcionários novos")
    public void cadastroFuncionariosNovos(DataTable dataTable) {

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