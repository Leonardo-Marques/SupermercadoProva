# mentoria-prova-Logica 
Neste projeto está contido uma pequena prova para testar os conhecimentos das pessoas cujo estão fazendo a capacitação de automação de testes. A prova é para a trilha de Lógica e Programação.

## Tecnologias utilizadas
A prova foi construída utilizando as seguintes tecnologias:
- Java (Ver. 1.8.0)
- IntelliJ (Ver. )

## Padrões Utilizados
- **Main** : Pasta onde estão as classes de fluxo do sistema.
- **Utils** : Pasta onde estão as classes de interação (leitura de arquivos e dados).
- **resources** : Pasta onde está o arquivo CSV, cujo estão listados todos os produtos do sistema.


## Fluxo do Sistema
 - A classe Main chama a Classe menu, onde ele controla o fluxo do sistema;
 - De acordo com a opção escolhida, são chamados métodos das classes Estoque e Pedido;
 - A classe Estoque faz a manutenção do estoque, que é adicionado através de um aquivo .CSV, cujo o mesmo está na pasta resources. Como o estoque armazena diversos produtos, a classe se relaciona em quase todos seus métodos com a classe Produto;
 - A classe Produto abstrai todas as informações de um produto;
 - A classe Pedido é como se fosse o carrinho de compras. Nela são guardados todos os itens disponíveis no estoque quais escolhemos comprar;
 - A classe Item recebe um Produto como atributo, a quantidade qual queremos comprar e o valor do mesmo. Itens são adicionados na classe Pedido.

 ## Abrindo o Projeto na IDE
 Este projeto foi feito no IntelliJ, porém, você pode usar a IDE de sua preferência.
 Se ocorrer algum erro la leitura do arquivo .CSV, extraia o projeto sem extrair para uma pasta em específico (extrair aqui).