
<h1 align="center"> Dscommerce </h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_Boot  V3.0.1-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
    <img src="https://img.shields.io/badge/JWT-F2F4F9?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=black"/>
    <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>   


[![NPM](https://img.shields.io/npm/l/react)](https://github.com/maririb749/dscommerce1/blob/main/LICENCE) 


# Sobre o projeto



Este projeto teve o objetivo de criar um API Rest e-commerce, utilizando os padrões de arquitetura em camadas, onde é possível cadastrar um cliente com nível de autorização. Esse cliente pode cadastrar produtos (caso seja administrador) e selecionar os mesmos produtos  para efetuar uma compra.


## Modelo Conceitual


![modelo_conceitual](https://user-images.githubusercontent.com/85500087/217897606-7284ce34-0fee-426e-8eea-cb8bce7a2cd5.png)

## Experimente live demo:

![cinnamon-20230215-133 (1)](https://user-images.githubusercontent.com/85500087/218984449-0d41c020-cd79-4383-a289-59b43a1f8b75.gif)


# Tecnologias utilizadas
## Backend
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- H2 Database
- Postman

<h2>Como criar e executar o DSCommerce localmente</h2>

Criar e executar o projeto em seu ambiente de desenvolvimento local é muito fácil. Certifique-se de ter o Git e JDK17 instalados e siga as instruções abaixo.


- Clone o código fonte

git@github.com:maririb749/dscommerce1.git

- Em sua IDE de preferência(utilizei Intellij), importe a pasta **backend** e faça o update das dependências do **maven**.

- Ao executar o projeto, pode ser acessado um navegador da Web em http://localhost:8080/h2-console

- Collections do postman para fazer as requisições GET/PUT/DELETE E UPDATE para criação do usuário, lançar as pedidos e consultar todos os produtos. Obs:    Será necessário configurar a variáveis de ambiente no postman.  

 - Dados para login: e-mail. 

 - senha: 123456
      
- Link da Collections do postman:https://elements.getpostman.com/redirect?entityId=19053874-0c6e1b77-f622-4f7b-8a0e-9c677895bcbc&entityType=collection

 

