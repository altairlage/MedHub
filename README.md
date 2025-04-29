
# MedHub

![Java](https://img.shields.io/badge/Java-21%2F22-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green?logo=springboot)
![Apache Kafka](https://img.shields.io/badge/Kafka-3.7-orange?logo=apachekafka)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue?logo=apachemaven)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue?logo=docker)

# Objetivo do Projeto

Este projeto faz parte da fase 3 do Tech Challenge da pós-graduação, cujo objetivo é criar uma solução de backend segura, modular e escalável para um ambiente hospitalar. O foco é garantir a comunicação assíncrona entre serviços usando Apache Kafka.

Este projeto inclui:
- Serviço de Agendamento (producer das mensagens de agendamento).
- Serviço de notificações de agendamento (consumer das mensagens de agendamento): `notifier-service`.
   - Notifica os pacientes quanto a criação, atualização e cancelamento de agendamentos.
- Topico Kafka para mensageria entre os serviços de Agendamento e Notificação.
- Serviço de Histórico de Consultas (opcional, via GraphQL).

O `notifier-service` é responsável por:
- Consumir mensagens de agendamentos do tópico Kafka `schedule-notification`.
- Transformar mensagens em objetos `ScheduleNotification` (DTO).
- Enviar notificações para pacientes sobre consultas agendadas.

# Tecnologias Utilizadas

- Java 21/22
- Spring Boot 3.2
- Apache Kafka 3.7
- Maven
- Docker

# Estrutura do Projeto

- **notifier-service**: Serviço que consome mensagens do Kafka referentes a agendamentos.
- **notifier-test-producer**: Projeto auxiliar que envia mensagens de teste ao Kafka.


## Notifier-Service


### Como Executar o Notifier-Service

#### 1. Subir o Apache Kafka com Docker

```bash
# Baixar e rodar o container Kafka
docker run --name medhub-schedule -p 9092:9092 -d -t apache/kafka:latest

# Acessar o container
docker exec -it medhub-schedule bash

# Criar o tópico "schedule-notification"
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic schedule-notification
```

#### 2. Executar o Notifier Service

- Abra o projeto `notifier-service` no IntelliJ IDEA.
- Execute a aplicação (`NotifierServiceApplication`).
- O Listener Kafka ficará aguardando mensagens no tópico `schedule-notification`.

#### 3. Executar o Producer de Testes

- Abra o projeto `notifier-test-producer` no IntelliJ IDEA.
- Execute a aplicação (`NotifierTestProducerApplication`).
- Esta aplicação gerará 10 mensagens de exemplo no tópico `schedule-notification`.

#### 4. Verificar o Consumo

- Verifique o console do `notifier-service`.
- As mensagens serão lidas, deserializadas e logadas como objetos `ScheduleNotification`.

#### 5. Finalizar o Ambiente

```bash
# Parar o container Kafka
docker container stop medhub-schedule

# Remover o container Kafka
docker container rm medhub-schedule
```

### Comandos Docker Utilizados

```bash
# Subir o container Kafka
docker run --name medhub-schedule -p 9092:9092 -d -t apache/kafka:latest

# Entrar no container
docker exec -it medhub-schedule bash

# Criar tópico Kafka
./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic schedule-notification

# Parar e remover container
docker container stop medhub-schedule
docker container rm medhub-schedule
```

---

_Desenvolvido para a pós-graduação em Engenharia de Software — Fase 3 do Tech Challenge._
