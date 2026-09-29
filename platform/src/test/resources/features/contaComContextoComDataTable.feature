#language:pt
@ContaTeste

Funcionalidade: Estes testes vão testar a funcionalidade de transações com valores variados

  Contexto: Cria a conta do cliente e realizar diversas movimentações
    Dado a conta criada o cliente "João" de número 001 com limite 0 e saldo 0
    E que foram realizadas as movimentacoes
      |valor  | tipo     |
      |100    | deposito |
      |50     | saque    |
      |20     | saque    |

    Cenario: Verifica as operações realizadas
      Entao o cliente tem saldo no valor de 30 na conta
      E a quantidade de depositos é 1
      E a quantidade de saques é 2
      E o numero de transacoes é 3
