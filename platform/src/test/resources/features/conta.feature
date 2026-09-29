#language:pt
@ContaTeste
Funcionalidade: Testar as operações básicas da conta de um cliente
  1 - O saque deve ser aprovado se o cliente tiver o saldo na conta

  @deposito
  Cenario: Testar o depósito do cliente
    Dado a conta criada o cliente "João" de número 001 com limite 0 e saldo 0
    Quando o cliente realiza o depósito no valor de 100 na conta
    Entao o cliente tem saldo no valor de 100 na conta

  @saque
  Cenario:  Testar o saque do cliente
    Dado a conta criada o cliente "João" de número 001 com limite 0 e saldo 0
    E fazendo um depósito no valor de 50
    Quando o cliente realiza o saque no valor de 20 na conta
    Entao o cliente tem saldo no valor de 30 na conta

