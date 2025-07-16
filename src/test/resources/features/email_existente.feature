Scenario: Cadastro com e-mail duplicado
Given que o usuário "chines@email.com" está cadastrado no sistema
When outro usuário tenta se cadastrar com o mesmo e-mail
Then a API deve responder com o status 409 CONFLICT
And o corpo da resposta deve conter a mensagem "E-mail já cadastrado"

