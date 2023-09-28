
# Gerenciador de Restaurantes_Derick_Castro

[![Docker Hub Repo](https://img.shields.io/docker/pulls/derickccastro/manager-restaurant.svg)](https://hub.docker.com/repository/docker/derickccastro/manager-restaurant)

![4452317](https://github.com/derick1castro/teste-tech/assets/104864411/05417ca6-48fa-44c6-a103-5190907d0004)

> Projeto denominado "Restaurant Manager". Este projeto abrange a criação de uma RESTful API sofisticada desenvolvida com Java 17, Spring Boot 3 e PostgreSQL, capaz de gerenciar os restaurantes e os produtos do seu cardápio.

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] CRUD de restaurantes, produtos, categorias e promoções;
- [x] Implementação da paginação;
- [x] Implementação da Spring HATEOAS;
- [x] Implementação da Metodologia de versionamento;
- [x] Documentação atráves do Swagger(OpenAPI);
- [x] "Dockerização" da Aplicação;
- [ ] Funcionalidade de filtros de pesquisas por nome e tags;
- [ ] Login com validação de email e senha assim como a autenticação;
- [ ] Testes unitarios utilizando Junit e Mockito.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:
* Você instalou a versão mais recente de `<JDK / PostgreSQL / PgAdmin / Docker>`
* Você tem uma máquina `<Windows / Linux / Mac>`.

## 🚀 Instalando Gerenciador de Restaurantes

Para instalar o Gerenciador de Restaurantes, siga estas etapas:

### `Certifique-se de que a máquina de destino tenha o Java Development Kit (JDK) instalado. Você pode verificar isso executando o comando java -version no terminal.

Copie o arquivo JAR ou WAR do seu aplicativo Spring Boot para a máquina de destino. Isso pode ser feito por meio de mídia removível, transferência de arquivo pela rede ou serviços de armazenamento em nuvem.

Abra um terminal na máquina de destino e navegue até o diretório onde o arquivo JAR ou WAR está localizado.

Execute o aplicativo Spring Boot usando o comando java -jar nome-do-arquivo.jar (substitua "nome-do-arquivo.jar" pelo nome real do arquivo). Isso iniciará o aplicativo na máquina de destino.

O aplicativo estará disponível localmente na máquina de destino, geralmente na porta padrão 8080, a menos que você tenha configurado uma porta diferente.`

Crie uma DataBase chamada restaurant-api no PostgreSQL pode ser utilizando o PgAdmin para maior facilidade, após isso, de um start no projeto.

## ☕ Usando Gerenciador de Restaurantes

Para usar Gerenciador de Restaurantes, siga a documentação atráves do Swagger(OpenAPI):

(http://localhost:8080/swagger-ui/index.html)

## 🚀 Desafios/problemas que ocorreram durante a execução do projeto

### Desafio 1: Problema de lazy loading: 

Descrição: Enfrentei um desafio relacionado ao carregamento preguiçoso (lazy loading) de dados ao trabalhar com nosso sistema. Isso resultou em consultas inesperadas(valores vazios) ao banco de dados e no desempenho inferior do sistema.

Ações Tomadas: Investi tempo em entender as estratégias de carregamento de dados do framework que estava utilizando. Refatoramos meu código para otimizar o carregamento de entidades e certificando que estaria capturando os dados da requisição e direcionando ao banco de dados.


### Desafio 2: Dúvidas entre utilizar o LOMBOK ou não:

Descrição: Estiive um dúvida sobre a adoção do projeto Lombok para simplificar o código e reduzir a verbosidade. No entanto, havia preocupações sobre possiveis bugs e não entendimento dos fundamentos projeto.

Ações Tomadas: Realizei uma análise detalhada do Lombok e seus benefícios. Decidi não implementar o Lombok no projeto para que não pudesse ter nenhum erro futuro e o mais importante trabalhar os fundamentos. Mantive a documentação atualizada para auxiliar no entendimento do código.


### Desafio 3: Problemas de relacionamento entre 3 entidades diferentes:

Descrição: Me deparei com desafios na gestão de relacionamentos complexos entre três entidades diferentes em nosso sistema. Esses relacionamentos levaram a problemas de integridade referencial e consultas complexas.

Ações Tomadas: Estudei bastante modelagem de dados e utilizamos as capacidades de mapeamento de relacionamento oferecidas pelo framework. Desenvolvemos estratégias claras de carregamento de entidades e adotei boas práticas de banco de dados para manter a integridade dos dados.


### Desafio 4: Problemas com o Spring Validation:

Descrição: Enfrentei desafios ao implementar a validação de dados usando o Spring Validation. A validação de entrada de dados dos usuários nem sempre estava sendo tratada corretamente, resultando em exceções inesperadas sobescrevendo as exceções personalizadas.

Ações Tomadas: Realizei uma revisão detalhada das anotações de validação e dos interceptadores do Spring. Aprofundei meu conhecimento sobre como o Spring Validation funciona. Também implementamos mensagens de erro personalizadas para melhorar a clareza das mensagens de validação.

```
Adicione comandos de execução e exemplos que você acha que os usuários acharão úteis. Fornece uma referência de opções para pontos de bônus!

## 📫 Contribuindo para Gerenciador de Restaurantes
Para contribuir com Gerenciador de Restaurantes, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Crie a solicitação de pull.


[⬆ Voltar ao topo](#Gerenciador de Restaurantes)<br>

