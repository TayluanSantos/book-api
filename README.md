# Book-api

## Projeto

O projeto foi desenvolvido a propósito de estudo e trata-se de uma API RESTful simples, o qual o usuário deve ser capaz de executar operações básicas de um CRUD,como:

- Criar um registro;
- Buscar um registro;
- Atualizar um registro;
- Excluir um registro;

Dessa forma, foi desenvolvido uma API que permitisse o usuário ter acesso e a possibilidade de cadastro dos seguintes campos:

- ``` bookName ``` : Referente ao nome do livro;
- ``` author ``` : Referente ao autor do livro;
- ``` price ``` : Referente ao preço do livro;


# Tecnologias

Para o desenvolvimento do projeto,foram utilizadas as seguintes ferramentas:

- Java 18
- Spring Web
- Spring Data JPA
- Validation
- H2 Database
- Lombok
- ModelMapper

# Funcionalidades

Como descrito anteriormente, o usuário poderá criar,buscar,atualizar e excluir um registro.

OBS: Lembrando que o projeto roda localmente na porta ```8080 ```

## Cadastrando um livro

Para cadastrar um novo livro, o usuário deve fazer uma requisição do tipo ```POST``` para o endpoint ```/api/v1/books```.Além disso,as informações devem ser passadas 
no corpo da requisição em formato ```JSON```

Ex: ```http://localhost:8080/api/v1/books ```

```
{
    "bookName": "O poder do hábito ",
    "author": "Charles Duhigg",
    "price": 42.90
}
```
Caso não haja nenhum erro,a resposta será retornada seguida do status ```201-CREATED```

```
{
    "bookId": 1,
    "bookName": "O poder do hábito ",
    "author": "Charles Duhigg",
    "price": 42.90
}
```
Em caso de erro durante o envio da requisição,será retornado o status ```400-BAD REQUEST```

## Buscando todos os livros cadastrados

Para buscar todos os livros cadastrados, o usuário deve fazer uma requisição do tipo ```GET``` para o endpoint ```/api/v1/books```.

Ex: ```http://localhost:8080/api/v1/books ```

Caso não haja nenhum erro,a resposta será retornada seguida do status ```200-OK```

```
{
 [
    {
        "bookId": 1,
        "bookName": "O poder do hábito ",
        "author": "Charles Duhigg",
        "price": 42.90
    },
    {
        "bookId": 2,
        "bookName": "Mindset: A nova psicologia do sucesso ",
        "author": "Carol S. Dweck",
        "price": 45.90
    }
]
}
```
Caso não tenha nenhum livro cadastrado, é retornado um array vazio.
```
[]

```
## Buscando um livro pelo id

Para buscar um livro específico , o usuário deve fazer uma requisição do tipo ```GET``` para o endpoint ```/api/v1/books/{id}```,passando um identificador único,nesse caso
,o id do livro, junto ao endereço da requisição.No exemplo a seguir,faremos a busca por um livro que tem o ```id=1```

Ex: ```http://localhost:8080/api/v1/books/1 ```

Caso não haja nenhum erro,a resposta será retornada seguida do status ```200-OK```

```
    {
        "bookId": 1,
        "bookName": "O poder do hábito ",
        "author": "Charles Duhigg",
        "price": 42.90
    }
   
```
Caso o livro buscado não conste no registro, será retornado uma mensagem de erro com o status ```404-NOT FOUND```.Nesse caso,levaremos em consideração que o usuário 
estaria tentando fazer uma busca por um livro de ```id=3```,que não está registrado.
```
{
    "message": "Book not found: 3",
    "path": "uri=/api/v1/books/3"
}

```
## Atualizando um livro

Para atualizar as informações sobre um livro, o usuário deve fazer uma requisição do tipo ```PUT``` para o endpoint ```/api/v1/books/{id}```.Dessa vez 
é necessário que o usuário envie as informações a serem atualizadas no corpo da requisição em formato ```JSON```,além de ter que passar o id no endereço da requisição.
Nesse caso,levaremos em consideração que o usuário está tentando atualizar o preço de um livro.

Ex: ```http://localhost:8080/api/v1/books/1 ```

```
{
    "bookName": "O poder do hábito ",
    "author": "Charles Duhigg",
    "price": 42.90
}
```
Caso não haja nenhum erro,a resposta será retornada seguida do status ```200-OK``` com as informações atualizadas.

```
{
    "bookId": 1,
    "bookName": "O poder do hábito ",
    "author": "Charles Duhigg",
    "price": 52.90
}
```
Caso o livro que o usuário está tentando atualizar as informações não esteja registrado, será retornado uma mensagem de erro com o status ```404-NOT FOUND```.
Nesse caso,levaremos em consideração que o usuário estaria tentanto atualizar as informações de um livro que possui o  ```id=3```,que não está registrado.
```
{
    "message": "Book not found: 3",
    "path": "uri=/api/v1/books/3"
}

```
## Deletando um livro

Para deletar um livro específico, o usuário deve fazer uma requisição do tipo ```DELETE``` para o endpoint ```/api/v1/books/{id}```.Nesse caso,excluiremos o livro que
possui o ```id=1``` dos registros.

Ex: ```http://localhost:8080/api/v1/books/1 ```

Caso não haja nenhum erro,será retornado o  status ```204-NO CONTENT``` com o corpo da resposta vazio.
```


```

Caso o livro que o usuário está tentando excluir não esteja registrado, será retornado uma mensagem de erro com o status ```404-NOT FOUND```.
Nesse caso,levaremos em consideração que o usuário estaria tentando excluir do registro um livro que possui o ```id=3```, que não está registrado.
```
{
    "message": "Book not found: 3",
    "path": "uri=/api/v1/books/3"
}

```








