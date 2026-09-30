#language:pt
@FoliumTeste
Funcionalidade: Geração de folha de pagamento
  Contexto:
    Dado que existe uma folha de pagamento de competência: 2026-09

  Cenario: Cadastrar funcionários com dados válidos
    Quando cadastrar os seguintes funcionários novos
      |matrícula  |nome                  |cargo          |salario base   |
      |001        |Mateus Lopes          |DIRET0R        |5000           |
      |002        |Maria Clara Barroso   |ANALISTA       |2800           |
      |003        |Bruna Soares          |GERENTE        |3500           |
    Entao 3 funcionários devem estar cadastrados com os dados informados
