#language:pt
@ContaTeste

Funcionalidade: Estes testes vão testar a funcionalidade de depósito com valores variados

  Contexto: Cria a conta com diversos depósitos
    Dado a conta criada o cliente "João" de número 001 com limite 0 e saldo 0
    E que foram realizados os depósitos
      |valor  |
      |100    |
      |50     |
      |20     |

    Cenario: Verificar o saldo final
      Entao o cliente tem saldo no valor de 170 na conta

    Cenario: Verificar o saldo e o histórico da conta
      Quando o cliente realiza os saques
      |valor|
      |50   |
      |20   |
      Entao o cliente tem saldo no valor de 100 na conta
      E numero de transacoes é 5