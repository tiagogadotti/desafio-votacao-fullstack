### README
# Desafio Votação

Este projeto é uma aplicação de votação com front-end em Angular, back-end em Spring Boot e banco de dados MySQL, orquestrados pelo Docker Compose.

## Dependências

### Windows

1. **Git**
   - Baixe e instale o [Git para Windows](https://gitforwindows.org/).
   - Após a instalação, verifique a instalação abrindo um terminal e digitando `git --version`.

2. **Docker**
   - Baixe e instale o [Docker Desktop para Windows](https://www.docker.com/products/docker-desktop).
   - Após a instalação, verifique a instalação abrindo um terminal e digitando `docker --version` e `docker-compose --version`.

### Linux

1. **Git**
   - Para distribuições baseadas em Debian/Ubuntu, use:
     ```sh
     sudo apt update
     sudo apt install git
     ```
   - Para distribuições baseadas em Red Hat/Fedora, use:
     ```sh
     sudo dnf install git
     ```
   - Após a instalação, verifique a instalação abrindo um terminal e digitando `git --version`.

2. **Docker**
   - Siga as instruções no [site oficial do Docker](https://docs.docker.com/engine/install/) para a sua distribuição.
   - Após a instalação, verifique a instalação abrindo um terminal e digitando `docker --version` e `docker-compose --version`.

## Passo a Passo de Execução

1. Clone o repositório:
   ```sh
   git clone https://github.com/tiagogadotti/desafio-votacao-fullstack
   cd desafio-votacao-fullstack
   ```

2. Construa e inicie os containers usando Docker Compose:
   ```sh
   docker-compose up --build
   ```

3. Aguarde até que todos os serviços estejam iniciados. O front-end Angular estará disponível na porta 80, o back-end Spring Boot na porta 8080, e o MySQL na porta 3307.

4. Acesse a aplicação no seu navegador:
   - Aplicação: [http://localhost](http://localhost)
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## URLs Importantes

- **Aplicação**: [http://localhost](http://localhost)
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Notas

- Certifique-se de que as portas 80, 8080 e 3307 estejam livres no seu sistema.
- Para encerrar os containers, use `Ctrl + C` no terminal onde o `docker-compose up` foi executado ou, em outro terminal, execute:
  ```sh
  docker-compose down
  ```


# Votação

## Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução we para gerenciar e participar dessas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por
  um tempo determinado na chamada de abertura ou 1 minuto por default)
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
  é identificado por um id único e pode votar apenas uma vez por pauta)
- Contabilizar os votos e dar o resultado da votação na pauta

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A solução deve ser construída em java com Spring-boot e Angular, mas os frameworks e bibliotecas são de livre escolha (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

## Como proceder

Por favor, realize o FORK desse repositório e implemente sua solução no FORK em seu repositório GItHub, ao final, notifique da conclusão para que possamos analisar o código implementado.

Lembre de deixar todas as orientações necessárias para executar o seu código.

### Tarefas bônus

- Tarefa Bônus 1 - Integração com sistemas externos
  - Criar uma Facade/Client Fake que retorna aleátoriamente se um CPF recebido é válido ou não.
  - Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos
  - Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação. Essa operação retorna resultados aleatórios, portanto um mesmo CPF pode funcionar em um teste e não funcionar no outro.

```
// CPF Ok para votar
{
    "status": "ABLE_TO_VOTE
}
// CPF Nao Ok para votar - retornar 404 no client tb
{
    "status": "UNABLE_TO_VOTE
}
```

Exemplos de retorno do serviço

### Tarefa Bônus 2 - Performance

- Imagine que sua aplicação possa ser usada em cenários que existam centenas de
  milhares de votos. Ela deve se comportar de maneira performática nesses
  cenários
- Testes de performance são uma boa maneira de garantir e observar como sua
  aplicação se comporta

### Tarefa Bônus 3 - Versionamento da API

○ Como você versionaria a API da sua aplicação? Que estratégia usar?

## O que será analisado

- Simplicidade no design da solução (evitar over engineering)
- Organização do código
- Arquitetura do projeto
- Boas práticas de programação (manutenibilidade, legibilidade etc)
- Possíveis bugs
- Tratamento de erros e exceções
- Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
- Uso de testes automatizados e ferramentas de qualidade
- Limpeza do código
- Documentação do código e da API
- Logs da aplicação
- Mensagens e organização dos commits
- Testes
- Layout responsivo

## Dicas

- Teste bem sua solução, evite bugs

  Observações importantes
- Não inicie o teste sem sanar todas as dúvidas
- Iremos executar a aplicação para testá-la, cuide com qualquer dependência externa e
  deixe claro caso haja instruções especiais para execução do mesmo
  Classificação da informação: Uso Interno



# desafio-votacao
