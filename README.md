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

### Logs
- Uma forma de personalizar os logs da sua aplicação, é criar um arquivo logback.xml dentro de resources.
- Exemplo:
```
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                %d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%msg%n
            </pattern>
        </encoder>
    </appender>
    <logger name="root" level="INFO"/>
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```
