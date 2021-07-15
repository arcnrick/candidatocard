# candidato-card

API que mostra test unitários simples em um CRUD que contém candidatos e cartões de crédito vinculados ao candidato. Nesse caso não existe a obrigatoriedade de um cartão ser somente de um candidato. A única validação existente é que um candidato pode ter vários cartões mas não podem se repetir por candidato. 

A API disponibiliza métodos dos 4 principais verbos que podem ser testado pelo PostMan.

Foi utilizado exemplo de test unitário para validar um cpf e um cartão de crédito.

Também foram utilizados os recursos do Lombok em uma das classes, para comparar o tanto de código de pode ser "dispensado".

Para testes, pode ser utilizado no Postman, os seguintes consumos:
POST: localhost:8083/api/private/candidato
{
  "nome": "Nome HIJK",
  "cpf": "05537776618",
  "observacao": "Teste HIJK",
  "cartoes": [
        {
            "descricao": "ELO",
            "numeroCartao": "4716.6530.7138.8543"
        },
        {
            "descricao": "DINNERS",
            "numeroCartao": "5417.4179.74277594 "
        }
    ]
}

GET: localhost:8083/api/private/candidato

GET: localhost:8083/api/private/candidato/1

PUT: localhost:8083/api/private/candidato/4
{
    "nome": "Nome ABCD",
    "cpf": "05537776618",
    "observacao": "Teste 6547",
    "cartoes": [
        {
            "descricao": "VISA",
            "numeroCartao": "4716.6530.7138.8543"
        },
        {
            "descricao": "MASTER",
            "numeroCartao": "5417.4179.74277594 "
        }
    ]
}

DELETE: localhost:8083/api/private/candidato/5