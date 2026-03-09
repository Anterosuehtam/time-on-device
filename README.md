# ⏱️ Time on Device API

Este é um projeto de API RESTful desenvolvido para gerenciamento e controle de tempo de uso de dispositivos pessoais.

## 🚀 Tecnologias Utilizadas

* **Java 21** (Linguagem principal)
* **Spring Boot** (Web, Data JPA)
* **MySQL** (Banco de dados relacional para persistência real)
* **Hibernate** (Mapeamento Objeto-Relacional)
* **Lombok** (Redução de código boilerplate como Getters/Setters)
* **Maven** (Gerenciamento de dependências)

## 🧠 Inteligência e Regras de Negócio

A API vai além do armazenamento básico, garantindo a integridade e validação dos dados diretamente no lado do servidor:

* **Controle de Tempo Diário:** Lógica dinâmica que busca o histórico de atividades do usuário no dia atual para impedir que novos registros ultrapassem o limite de uso estipulado.
* **Barreira Anti-Clones (E-mail Único):** Uso de *Derived Query Methods* (`existsByEmail`) para validar e bloquear a criação ou atualização de múltiplos usuários com a mesma credencial.
* **Atualização Parcial Inteligente:** Blindagem nos métodos de `PUT` que ignora valores nulos recebidos via JSON, protegendo dados sensíveis já consolidados no banco.
* **Cálculo Automático de Duração:** Abstração da lógica matemática de conversão de tempo (`Duration.between`) no Back-end, removendo essa responsabilidade do usuário.

## Como Executar

### Pré-requisitos
* Java JDK 17 ou superior instalado.
* MySQL Server rodando localmente (na porta padrão `3306`).
* Um banco de dados criado no MySQL com o nome `time_on_device`.

### Passo a passo
1. Clone o repositório:
   ```bash
   git clone [https://github.com/Anterosuehtam/time-on-device.git](https://github.com/Anterosuehtam/time-on-device.git)
2. Acesse a pasta do projeto:
   ```bash
   cd time-on-device
   
3. Configure as credenciais do seu MySQL no arquivo:
   ```bash
     src/main/resources/application.properties (ajuste o username e password conforme o seu ambiente).

4. Execute a aplicação usando o Maven:
   ```bash
     ./mvnw spring-boot:run
  

5. A API estará rodando em: `http://localhost:3306`

### Exemplo de JSON (Body para POST em Usuários)
```json
{
  "name": "Matheus Antero",
  "email": "matheus@email.com",
  "dailyLimitMinutes": 120
}
    
