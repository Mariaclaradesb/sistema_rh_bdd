# language: pt
@FoliumTeste
Funcionalidade: Geração de folha de pagamento

  Contexto:
    Dado que existe uma folha de pagamento de competência: 2026-09

  Cenário: Calcular os totais da folha
    Dado que existem as seguintes folhas de pagamento
      | matrícula | nome                | cargo    | salario base | faltas |
      | 001       | Mateus Lopes        | DIRETOR  | 5000         | 0      |
      | 002       | Maria Clara Barroso | ANALISTA | 2800         | 3      |
      | 003       | Bruna Soares        | GERENTE  | 3500         | 6      |
    Quando calculo os totais da folha
    Então 3 funcionários devem estar cadastrados com os dados informados
    E o total dos salários base deve ser 11300
    E o total líquido da folha deve ser 8290

  Esquema do Cenário: Aplicar desconto por faixa salarial
    Quando cadastrar o funcionário "Ana Souza" com matrícula "010", cargo "ANALISTA", salário base <salário> e 0 faltas
    Então o desconto por faixa salarial deve ser <desconto>
    E o salário líquido deve ser <líquido>

    Exemplos:
      | salário | desconto | líquido |
      | 2000    | 0        | 2000    |
      | 2800    | 210      | 2590    |
      | 3500    | 525      | 2975    |
      | 4000    | 900      | 3100    |
      | 5000    | 1375     | 3625    |