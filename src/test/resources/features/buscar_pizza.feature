Scenario: Buscar pizza pelo nome
Given que as pizzas "mussarela", "calabresa" e "frango" estão cadastradas
When o cliente envia uma requisição GET para /pizzas?nome=mussarela
Then a resposta deve conter a pizza "mussarela"
And a resposta não deve conter as outras pizzas

