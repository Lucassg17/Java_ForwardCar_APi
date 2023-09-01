#language: pt

Funcionalidade: Testando metodos HTTP da API ForwardCar
  Contexto:
  * que crio um registro
  |planilha                       |aba     |
  |MassaDeDados_criarRegistro.xlsx|Registro|

  @Teste001 @%ForwardCar
  Cenario: Realizando um Post em Emprestimo
    Quando crio meu login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    E envio os dados para realizar um metodo Post em Emprestimo
      |planilha                       |aba       |
      |MassaDeDados_criarRegistro.xlsx|Emprestimo|
    Entao confirmo que meu post foi feito com sucesso validando o retorno do meu body

  @Teste002 @%ForwardCar
  Cenario: Realizando um Get em Emprestimos
    Quando realizo o login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    E envio um metodo Get para Emprestimo
      |planilha                       |aba       |
      |MassaDeDados_criarRegistro.xlsx|Emprestimo|
    Entao ao enviar a requisicao valido o retorno do meu body

  @Teste003 @%ForwardCar
  Cenario: Realizando um Post em Login
    Quando crio um metodo Post em Login
      |planilha                       |aba  |
      |MassaDeDados_criarRegistro.xlsx|Login|
    Entao valido que meu login foi criado com sucesso



