## Security com token
```
Objetivo desse projeto

##Contruir uma API que realiza authentication e authorization com token```
## Requisitos
```
Java 1.8

docker: Para subir o banco com postgres
docker-compose up

docker: Para derrubar o banco
docker-compose down

```

# Recursos para testar a api
```
POST  http://localhost:8080/login
GET   http://localhost:8080/v1/protected/estudents?page=0
POST  http://localhost:8080/v1/admin/estudents/1
DELET http://localhost:8080/v1/admin/estudents/1

```
#### Criar usuário para authenticar na api

USUARIO: ADMIN
username: toyo
password:devdojo

INSERT INTO public.usuario
(id, "admin", "name", "password", username)
VALUES(1, true, 'toyo','$2a$10$Umt8cyNWdtZHZ.U2gYSGA.E6cjw.kMN.Luj3A9VQTtWierkTEQcea','toyo');

USUARIO: USER
username: oda
password:devdojo

INSERT INTO public.usuario(id, "admin", "name", "password", username)
VALUES(2, false, 'oda','$2a$10$Umt8cyNWdtZHZ.U2gYSGA.E6cjw.kMN.Luj3A9VQTtWierkTEQcea','oda');

```

#### Crie um token no recurso abaixo, veja o token criado no Headers no body ou no header
```
POST  http://localhost:8080/login

{
	"username":"oda",
	"password":"devdojo"
}

Body  ou header

BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2RyaWd1ZXMiLCJleHAiOjE1NTQzMDAyOTV9.Socbf9mvPcjhagJH7fGRPAXWnJfpeB1WBDnDBR9Er4y9aQjsfoVM1CIkEix0lFNCIhNKTjcNDjAmQhAzdlj4SQ

```

#### Insira  estudents
```

Copie o token gerado no recurso login e cole nesse recurso no postman na part de Auth escolha Bearer Token

logar como admin para executar o recurso POST:

POST http://localhost:8080/v1/admin/estudents

{"name":"Adriano","email":"adri@gmail.com"}

```

``

#### Listar os estudents
```

Copie o token gerado no recurso login e cole nesse recurso no postman na part de Auth escolha Bearer Token

logar como admin ou user para executar o recurso GET:

GET http://localhost:8080/v1/protected/estudents?page=1

{
    "content": [
        {
            "name": "Adriano",
            "email": "adri@gmail.com",
            "id": 1
        }
    ],
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "sort": null,
    "first": false,
    "numberOfElements": 4,
    "size": 5,
    "number": 1
}

```

#### Deletar  estudents
```

Copie o token gerado no recurso login e cole nesse recurso no postman na part de Auth escolha Bearer Token

logar como admin para executar o recurso DELETE:

DELETE http://localhost:8080/v1/admin/estudents/1

{"name":"Adriano","email":"adri@gmail.com"}

```