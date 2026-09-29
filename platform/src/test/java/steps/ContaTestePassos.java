package steps;

import com.example.platform.modelos.Conta;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTestePassos {
    private Conta conta;
    private Exception erro;


    @Dado("a conta criada o cliente {string} de número {int} com limite {double} e saldo {double}")
    public void criar_conta_para_o_cliente(String nome, int numero, Double limite, Double saldo){
        conta = new Conta(nome, numero, limite, saldo);
    }

    @E("fazendo um depósito no valor de {double}")
    @Quando("o cliente realiza o depósito no valor de {double} na conta")
    public void o_cliente_realiza_deposito_na_conta(Double valor){
        conta.depositar(valor);
    }

    @Quando("o cliente realiza o saque no valor de {double} na conta")
    public void realizar_saque_na_conta(Double valor){
        try {
            conta.sacar(valor);
        }catch (RuntimeException erro){
            this.erro = erro;
        }
    }

    @Entao("o cliente tem saldo no valor de {double} na conta")
    public void verificar_saldo(Double valor){
        assertEquals(valor, conta.getSaldo());
    }

    @Entao("deve ocorrer um erro {string}")
    public void verificar_Erro(String mensagem){
        assertNotNull(this.erro);
        assertEquals(mensagem, this.erro.getMessage());
    }

    @Entao("numero de transacoes é {int}")
    public void verificar_numero_transacoes(int valor){
        assertEquals(valor, conta.getvalorTransacoes());

    }

    @E("que foram realizados os depósitos")
    public void realizar_depositos(DataTable dataTable){
        var depositos = dataTable.asMaps(String.class, String.class);
        for(var deposito : depositos) {
            double valor = Double.parseDouble(deposito.get("valor"));
            conta.depositar(valor);
        }
    }

    @Quando("o cliente realiza os saques")
    public void realizar_saques(DataTable dataTable){
        var saques = dataTable.asMaps(String.class, String.class);
        for(var saque : saques) {
            double valor = Double.parseDouble(saque.get("valor"));
            realizar_saque_na_conta(valor);
        }
    }

    @E("que foram realizadas as movimentacoes")
    public void  realizar_movimentacoes(DataTable dataTable){
        var movimentacoes = dataTable.asMaps(String.class, String.class);
        for(var movimentacao : movimentacoes){
            String tipo = movimentacao.get("tipo");
            double valor = Double.parseDouble(movimentacao.get("valor"));
            if(tipo.equalsIgnoreCase("Saque")){
                realizar_saque_na_conta(valor);
            } else {
                if(tipo.equalsIgnoreCase("deposito")){
                    o_cliente_realiza_deposito_na_conta(valor);
                }
            }
        }
    }

    @E("a quantidade de depositos é {int}")
    public void verificar_quantidade_depositos(int valor){
        assertEquals(valor, conta.getQuantidadeDepositos());
    }

    @E("a quantidade de saques é {int}")
    public void verificar_quantidade_saques(int valor){
        assertEquals(valor, conta.getQuantidadeSaques());
    }

    @E("o numero de transacoes é {int}")
    public void verificar_quantidade_transacoes(int valor){
        assertEquals(valor, conta.getvalorTransacoes());
    }



}
