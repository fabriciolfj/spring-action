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
### Spring integration
- Responsável pela integração com sistemas externos, seja: arquivos, apis ou mensageria.
- Utiliza-se canais (que são configurações de entrada e saida de dados) para o fluxo de integração.
- Os canais serve como conduíte, que conecta o transformador com o adaptador de canal de saída.
- Os componentes envolvidos, na maioria dos casos, são:
  - gateway
  - canais
  - transformador
  - adaptador de canal 
- Exemplo abaixo (configuração xml):
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:int="http://www.springframework.org/schema/integration"
  xmlns:int-file="http://www.springframework.org/schema/integration/file"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/integration
    http://www.springframework.org/schema/integration/spring-integration.xsd
    http://www.springframework.org/schema/integration/file
    http://www.springframework.org/schema/integration/file/spring-integration-file.xsd">

  <int:channel id="textInChannel" />

  <int:transformer id="upperCase"
      input-channel="textInChannel"
      output-channel="fileWriterChannel"
      expression="payload.toUpperCase()" />

  <int:channel id="fileWriterChannel" />

  <int-file:outbound-channel-adapter id="writer"
    channel="fileWriterChannel"
    directory="/tmp/sia6/files"
    mode="APPEND"
    append-new-line="true" />

</beans>
```

- Interface utilizada pela configuração acima:
```
@MessagingGateway(defaultRequestChannel = "textInChannel")
public interface FileWriterGateway {

    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}

```
