Scenario: Login com credenciais corretas
Given que existe um usuário cadastrado com e-mail "china@email.com" e senha "chinaInBox123erick@"
When ele envia esses dados para o endpoint POST /login
Then o sistema deve retornar status 200 OK
And o corpo da resposta deve conter um token JWT válido

