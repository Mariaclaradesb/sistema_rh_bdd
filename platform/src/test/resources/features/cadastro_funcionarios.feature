#language:pt
Funcionalidade: Cadastro de Funcionários dentro do Folium
    Contexto: Dado que não existem funcionários cadastrados ainda

    Cenario: Cadastrar funcionários com dados válidos
      Quando cadastrar os seguintes funcionários novos
        |matrícula  |nome            |cargo          |salario base   |
        |001        |Mateus Lopes    |DIRET0R        |5000           |
        |002        |Clara Barroso   |ANALISTA       |2800           |
        |003        |Bruna Soares    |GERENTE        |3500           |
      Entao 3 funcionários devem estar cadastrados com os dados informados

    Esquema do Cenario: Recusar salário inválido
      Quando tentar cadastrar o funcionário "<nome>" com matrícula "<matrícula>", cargo "<cargo>" e salário base <salário>
      Entao o cadastro deve ser recusado
      E a mensagem deve ser "O salário deve ser maior que zero"

      Exemplos:
        |nome         |matrícula |cargo       |salário   |
        |Douglas Moura|004       |ASSISTENTE  |0         |
        |Lucas Aguiar |004       |ANALISTA    |-69       |