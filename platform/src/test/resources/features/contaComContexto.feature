#language:pt
  @ContaTeste

  Funcionalidade: Estes testes vão testar a funcionalidade de depósito

    Contexto:
      Dado a conta criada o cliente "João" de número 001 com limite 0 e saldo 0

    @deposito
    Cenario: Testar o depósito do cliente
      Quando o cliente realiza o depósito no valor de 100 na conta
      Entao o cliente tem saldo no valor de 100 na conta

    @deposito
    Cenario: Testar o depósito do cliente analisando a transação
      Quando o cliente realiza o depósito no valor de 100 na conta
      Entao o cliente tem saldo no valor de 200 na conta
      E numero de transacoes é 5

