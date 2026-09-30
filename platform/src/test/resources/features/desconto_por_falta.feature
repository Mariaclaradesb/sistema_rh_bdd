# language: pt
@FoliumTeste
Funcionalidade: Desconto por falta

  Contexto:
    Dado que existe uma folha de pagamento de competência: 2026-09

  Esquema do Cenário: Descontar cem reais por falta
    Quando cadastrar o funcionário "Ana Souza" com matrícula "010", cargo "ANALISTA", salário base 2000 e <faltas> faltas
    Então o desconto por faltas deve ser <desconto>
    E o salário líquido deve ser <líquido>

    Exemplos:
      | faltas | desconto | líquido |
      | 0      | 0        | 2000    |
      | 1      | 100      | 1900    |
      | 3      | 300      | 1700    |