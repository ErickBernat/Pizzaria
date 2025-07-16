Scenario: Solicitação de Pedido válido
Given que o cliente com ID 1 está cadastrado
And escolheu uma pizza de calabresa com ID 3
When ele envia o pedido para o endpoint POST /pedidos
Then o pedido deve ser registrado no banco de dados
And o status do pedido deve ser "Recebido"

