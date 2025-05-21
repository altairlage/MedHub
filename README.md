
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

- **medsched**: Serviço de agendamento que produz mensagens no tópico Kafka.
- **notifier-service**: Serviço que consome mensagens do Kafka referentes a agendamentos e envia as notificações via email.

## Como Executar o Projeto

### Pré-requisitos

- **Docker** e **Docker Compose** instalados.
- **Java 21** ou superior.
- **Maven** instalado.

### Passos para Execução

#### 1. Subir os Serviços com Docker Compose

No diretório raiz do projeto, execute:

```bash
docker-compose up --build
```

#### 2. Verificar o Consumo

No diretório raiz do projeto, execute:

```bash
docker-compose logs -f 
```

#### 3. Finalizar o Ambiente

No diretório raiz do projeto, execute:

```bash
docker-compose down -v  
```

_Desenvolvido para a pós-graduação em Engenharia de Software — Fase 3 do Tech Challenge._
