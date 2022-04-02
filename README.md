<h1 align="center">Brasil Paralelo OpenAPI 3.x</h1>

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/pt/d/d6/Logo_BPL.png" alt="brasil-paralelo-logo" width="120px" height="120px"/>
  <br>
  <i>Brasil Paralelo OpenAPI 3.x
  <br>using
  <br><a href="https://clojure.org/">Clojure</a> and <a href="https://luminusweb.com/">Luminus Web Framework</a>.
  </i>
</p>

<p align="center">
  <a href="https://www.jpventura.com"><strong>www.jpventura.com</strong></a>
  <br>
</p>

<p align="center">
  <a href="CONTRIBUTING.md">Contributing Guidelines</a>
  ·
  <a href="https://github.com/jpventura/rossetta-petstore-luminus/issues">Submit an Issue</a>
  ·
  <a href="https://blog.jpventura.com/">Blog</a>
  <br>
  <br>
</p>

<p align="center">
  <a href="https://www.npmjs.com/@angular/core">
    <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" alt="Apache 2.0 License" />
  </a>&nbsp;
  <a href="https://circleci.com/gh/jpventura/workflows/rossetta-petstore-luminus/tree/master">
    <img src="https://img.shields.io/circleci/build/github/jpventura/rossetta-petstore-luminus/master.svg?logo=circleci&logoColor=fff&label=CircleCI" alt="CI status" />
  </a>&nbsp;
</p>

## [Changelog](docs/CHANGELOG.md)
Require to setup a predefined changelog script using the project main language.

## [Contributing](docs/CONTRIBUTING.md)
Require setup a license and CLA action at GitHub.

## Setup

Just run `docker-compose` locally:

```bash
    docker-compose build openapi
    docker-compose up
```

## Usage

First authenticate one of the avaiable users to generate a JWT token:

```bash
http http://0.0.0.0:3000/api/authorize \
    email=sconnor@aol.com \
    password=Iru0Dahng3Eepiel
```

You may access the API through:

  - [HTTPie CLI](https://httpie.io/docs/cli)
  - [Postman App](https://www.postman.com/)
  - [Swagger 2.x local host](http://0.0.0.0:3000/docs/index.html)

The response is a [JWT](https://jwt.io/) token  

```json
{
  "token": "eyJhbGciOiJBMjU2S1ciLCJlbm...QGXQzlPV_C9eQXHFORRVMA"
}
```

containing the following encrypted information

```JSON
{
  "header": {
    "alg": "A256KW",
    "enc": "A128GCM"
  },
  "payload": {
    "admin": false,                                  // Admin privileges
    "exp": 1648852056,                               // Expiration timestamp
    "iat": 1648848456,                               // Issued at timestamp
    "type": "patriot",                               // (basic, premium, patriot, patron)
    "sub": "26bd2f1f-98a5-4c65-9baf-347fa43f5194"    // User ID
  }
}
```

The token contain all information required to authentication and RBAC authorization.
So users will obtain different outputs for the same endpoint:

```bash
http http://0.0.0.0:3000/api/media/ \
    Authorization:'Token eyJhbGciOiJBMjU2S1ciLCJlbm...QGXQzlPV_C9eQXHFORRVMA'
```

```JSON
[{
"id": "78e34217-8be2-46f0-b4b8-1f654fcdd591",
"name": "CORINGA - Podcast Cultura Paralela #1",
"released_at": "2020-03-29T20:02:34",
"type": "podcast"
},
{
"id": "abeff106-6b4e-4f02-9b8b-2f4844af2f49",
"name": "Brasil - A Ãšltima Cruzada",
"released_at": "2021-07-08T16:37:11",
"type": "series"
},
{
"id": "95f80b8c-fae2-479a-a7d4-3191178018de",
"name": "Qual o limite do Respeito?",
"released_at": "2020-03-29T20:02:34",
"type": "debate"
},
{
"id": "801486de-892d-4969-933d-37e12d62065d",
"name": "INTERESTELAR - Podcast Cultura Paralela #2",
"released_at": "2020-03-29T20:02:34",
"type": "podcast"
},
{
"id": "ef1195f8-dd0f-429c-a26e-66f07687dbbc",
"name": "Congresso Brasil Paralelo - Rodrigo gurgel",
"released_at": "2021-01-24T11:45:57",
"type": "interview"
}]
```

A prepopulated database will be available with the following users:

| **name**            | **email**            | **password**     |
|---------------------|----------------------|------------------|
| Miles Bennett Dyson | mdyson@cyberdyne.com | aiH0Diuchik2Quoo |
| Sarah Connor        | sconnor@aol.com      | Iru0Dahng3Eepiel |
| John Connor         | jconnor@yahoo.com    | Eez3xahfaero9eih |
| Kyle Reese          | reese@skynet.com     | Zahjo6joh4Gah8bu |
