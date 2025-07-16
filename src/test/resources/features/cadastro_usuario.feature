Scenario: Cadastro de novo usuário com dados válidos
Given que o visitante acessa o endpoint POST /usuarios
When ele envia os dados nome, e-mail, senha e endereço no corpo da requisição
Then deve receber um status 201 CREATED
And o usuário deve ser cadastrado com sucesso

