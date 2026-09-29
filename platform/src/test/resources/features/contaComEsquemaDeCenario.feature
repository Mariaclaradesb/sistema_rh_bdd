#language:pt
@ContaTeste
Funcionalidade: Testar as operações de saque do cliente com sucesso

  Esquema do Cenario: Testar o saque do cliente
    Dado a conta criada o cliente "<cliente>" de número <numero_conta> com limite <limite> e saldo <saldo>
    E o cliente realiza o depósito no valor de <deposito> na conta
    Quando o cliente realiza o saque no valor de <saque> na conta
    Entao o cliente tem saldo no valor de <saldo_final> na conta

    Exemplos:
    |cliente|numero_conta|limite|saldo|deposito|saque|saldo_final|
    |João   |001         |0     |0    |100     |50   |50         |
    |Anna   |002         |0     |100  |10      |10   |111        |