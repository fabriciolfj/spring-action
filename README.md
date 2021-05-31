# Spring action
- Este repositório tem por objetivo explorar os projetos do spring.

## Configurações
- spring utiliza as configurações e as disponibilizam em seus beans dentro do seu contexto.
- a configuração em spring, podem existir em diversas fontes, como:
  - arquivo application.properties ou application.yml
  - argumentos na execução do arquivo jar
  - variável de ambiente

- Exemplos:
```
applicatin.properties: server.port=9090
java -jar app.jar --server.port=9090
export SERVER_PORT=9090
```
