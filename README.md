
<h1 align="center"> Dscommerce </h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_BootV2.7.3-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
    <img src="https://img.shields.io/badge/JWT-F2F4F9?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=black"/>
    <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>   


[![NPM](https://img.shields.io/npm/l/react)](https://github.com/maririb749/dscommerce1/blob/main/LICENCE) 


# Sobre o projeto



 A aplicação é baseada em um comércio eletrônico, sua regra de negócio solicita que o usuário acesse autenticado ou não, ele terá acesso a página  dos produtos, podendo adicioná-los no carrinho de compras. Caso esteja logado, o pedido pode ser finalizado. Aplicação disponibiliza dois tipos de acesso que são para o usuário comum logado ou  o admin. O admin pode realizar cadastro, atualização e exclusão de produtos da aplicação, enquanto o usuário logado comum pode incluir e remover itens do carrinho de compras, bem como alterar as quantidades de cada item.


## Modelo Conceitual


![modelo_conceitual](https://user-images.githubusercontent.com/85500087/217897606-7284ce34-0fee-426e-8eea-cb8bce7a2cd5.png)

## Experimente live demo:

![cinnamon-20230215-133 (1)](https://user-images.githubusercontent.com/85500087/218984449-0d41c020-cd79-4383-a289-59b43a1f8b75.gif)


## Como criar e executar o DSCommerce localmente;

Criar e executar o projeto em seu ambiente de desenvolvimento local é muito fácil. Certifique-se de ter o Git e JDK17 instalados e siga as instruções abaixo.


- Clone o código fonte

 git@github.com:maririb749/dscommerce1.git

- Em sua IDE de preferência(utilizei Intellij), importe a pasta **backend** e faça o update das dependências do **maven**.

- Ao executar o projeto, pode ser acessado um navegador da Web em http://localhost:8080/h2-console

- Collections do postman para fazer as requisições GET/PUT/DELETE E UPDATE para criação do usuário, lançar as pedidos e consultar todos os produtos.

- Dados para login: maria@gmail.com (cliente) e alex@gmai.com (cliente e administrador).

- senha: 123456

       
- Link da Collections do postman:https://api.postman.com/collections/19053874-9ea0ddb4-e2a7-4d31-a101-69b0432e9fa8?access_key=PMAT-01GWPGNF6Z5NRSDN5CEX8FDEWC

## Tecnologias utilizadas
### Backend
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- H2 Database
- Postman
- OAuth2 
- JWT


 

