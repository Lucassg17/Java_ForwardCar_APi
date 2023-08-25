#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar

  @Teste001 @%ForwardCar
  Cenario: Realizando um Get em Emprestimos
    Dado que crio um registro
        |planilha                       |aba     |
        |MassaDeDados_criarRegistro.xlsx|Registro|
    E preencho o body da minha requisicao com os dados que criei
    Quando realizo o login
    Entao ao enviar uma requisicao Get para Emprestimos valido que a acao ocorreu com sucesso