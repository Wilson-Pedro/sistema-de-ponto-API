# Sistema-de-ponto-API
API de Sistema de Ponto: Uma interface de programação de aplicativos (API) que permite o registro e o acompanhamento das horas de trabalho dos funcionários, 
facilitando o controle de presença, a gestão de folha de pagamento e o monitoramento da produtividade da equipe em uma organização.

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven

## Banco de dados
-H2 
-PostgresSQL

Para rodar o projeto localmente faça o download das seguintes ferramentas:

- [STS](https://spring.io.xy2401.com/tools3/sts/all/)
- [Postman](https://www.postman.com/downloads/)
- [Git](https://git-scm.com/downloads)

Depois de ter instalado as ferramentas, crie uma pasta e dê um nome a ela. Então abra o seu git bash nessa pasta. e digite o seguinte comando:

![ABRIR-GIT-BASH](https://github.com/Wilson-Pedro/images/blob/main/git-bash/abrir-git-bash.png)

```bash
git clone git@github.com:Wilson-Pedro/sistema-de-ponto-API.git
```

Após isso abra o projeto no STS ou qualquer IDE que suporte o SPRING.

-> Com o STS vá em 'sistema-de-ponto-API.java' e clique com o potão direito.

-> Vá em 'Run As'.

-> E clique em 'Spring Boot App'

# Abra o [Postman](https://www.postman.com/downloads/) e teste o seguintes endpoints:
# POST
```

http://localhost:8080/registros/1/entrada

```

```

http://localhost:8080/registros/1/saida

```

# GET ONE
```

http://localhost:8080/pagamentos/1

```

## Autor

- [@Wilson Pedro](https://github.com/Wilson-Pedro)

## Linkedin
- [Wilson Pedro](https://www.linkedin.com/in/wilson-pedro-976333226/)
