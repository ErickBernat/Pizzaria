Scenario: Pedido com pizza inexistente
Given que o cliente está cadastrado
When ele tenta fazer um pedido com o ID da pizza inexistente
Then o sistema deve retornar status 404 NOT FOUND
And uma mensagem "Pizza não encontrada"

