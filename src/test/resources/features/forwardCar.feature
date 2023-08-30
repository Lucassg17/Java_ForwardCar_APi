#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar

  @Teste001 @%ForwardCar
  Cenario: Realizando um Post em Emprestimo
    Dado que crio um registro
      |planilha                       |aba     |
      |MassaDeDados_criarRegistro.xlsx|Registro|
    Quando crio meu login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    E envio os dados para realizar um metodo Post em Emprestimo
      |planilha                       |aba       |
      |MassaDeDados_criarRegistro.xlsx|Emprestimo|
    Entao confirmo que meu post foi feito com sucesso validando o retorno do meu body

  @Teste002 @%ForwardCar
  Cenario: Realizando um Get em Emprestimos
    Dado que crio um registro
      |planilha                       |aba     |
      |MassaDeDados_criarRegistro.xlsx|Registro|
    Quando realizo o login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    E envio um metodo Get pata Emprestimo
    Entao ao enviar uma requisicao Get para Emprestimos valido que a acao ocorreu com sucesso

  @Teste003 @%ForwardCar
  Cenario: Realizando um Get em Fabrica
    Dado que realizo um metodo Get em Fabrica
    Entao valido o tamanho da lista de fabricas que retorna no body do meu response

  @Teste004 @%ForwardCar
  Cenario: Realizando um Post em Login
    Dado que crio um registro
      |planilha                       |aba     |
      |MassaDeDados_criarRegistro.xlsx|Registro|
    Quando crio um metodo Post em Login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    Entao valido que meu login foi realizado com sucesso

  @Teste005 @%ForwardCar
  Cenario: Realizando um Get em Modelo
    Dado que realizo um metodo Get em Modelo

