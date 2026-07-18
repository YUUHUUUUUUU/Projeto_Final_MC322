João Gilberti Alves Tavares - 288885
Rafael Martins Ruas - 204824
Antônio Victor Amorim Marques - 288660
Enzo Farina Mullis - 243589

# UEPA - União de Entidades, Projetos e Atividades

Um sistema desktop desenvolvido em **Java** e **JavaFX** para gerenciar, descobrir e notificar atividades extracurriculares dentro do contexto do **UEPA (União de Entidades, Projetos e Atividades)**. O aplicativo adota conceitos sólidos de Programação Orientada a Objetos (POO), arquitetura MVC (Model-View-Controller) e padrões de projeto para conectar estudantes a oportunidades de forma eficiente.

---

## Principais Funcionalidades

* **Autenticação e Registro Seguro:** Controle de acesso com regras rígidas de validação de e-mail e requisitos de senha forte (letras maiúsculas, minúsculas, números e símbolos).
* **Catálogo de Atividades Dinâmico:** Visualização em cards gerados dinamicamente das modalidades como Estágios (`Internship`), Iniciação Científica (`ScientificInit`), Esportes (`Sports`) e Centros Acadêmicos (`StudentAssociation`).
* **Sistema de Notificações Interno (Backend):** Comunicação direta entre a coordenação (`Staff`) e os alunos inscritos via caixas de mensagens (`Mailbox`) integradas por meio do padrão *Observer*. 
  * *Nota de Implementação:* A lógica de backend (distribuição e recebimento de mensagens) está totalmente funcional e testada. No entanto, a interface gráfica (GUI) para visualização da Mailbox não foi implementada nesta etapa do projeto.
* **Persistência Local (XML):** Armazenamento seguro de dados cadastrais, logs de atividades e históricos de mensagens utilizando leitura e escrita via arquivos XML nativos (StAX).
* **Navegação e UX (User Experience):** Troca fluida de telas gerenciada por um `SceneManager` inteligente, que mantém o histórico de navegação (botão voltar) e exibe mensagens de erro amigáveis com animações suaves de *FadeTransition*.

---

## Estrutura e Arquitetura do Projeto

O projeto está modularizado seguindo o padrão MVC e dividindo pacotes por responsabilidades:

### Código Fonte (`src/main/java/uepa/aplicativo/`)
* **`controllers/`:** Controladores do JavaFX (`LoginController`, `RegisterController`, `CatalogController`, etc.). Lidam com os eventos da interface.
* **`DataManager/`:** Gerenciamento de estado em memória (`Data`) e persistência local (`xmlReader`, `xmlWriter`).
* **`Exceptions/`:** Exceções customizadas de negócio (`InvalidEmailException`, `InvalidPasswordException`, `ImageException`) para garantir integridade.
* **`extracurricular/`:** Modelos de atividades extracurriculares e domínio principal.
* **`user/`:** Gestão de perfis (`Student`, `Staff`) e aplicação das regras de negócio (`UserManager`).
* **`message/` & `interfaces/`:** Domínio do sistema de mensagens e contratos (`Notificable`, `notify`, `RecieveData`).
* **`SceneManager/` & `loaders/`:** Classes utilitárias responsáveis pelo roteamento de telas, manutenção do histórico de janelas (`previousScene`) e carregamento otimizado de recursos visuais.

### Recursos (`src/main/resources/`)
* **`fxml/`:** Arquivos de layout das telas criados no SceneBuilder (Login, Catálogo, Cards, etc.).
* **`fonts/`:** Tipografia customizada da aplicação (família *Manrope*).
* **`logo/` & `images/`:** Ícones da aplicação, banners e logos das atividades.
* **`xml/`:** Banco de dados local contendo os registros salvos (`user.xml`, `extra.xml`).

### Testes (`src/test/java/uepa/aplicativo/`)
Cobertura de testes unitários utilizando JUnit 5:
* **`DataManager/DataTest.java`**
* **`extracurricular/ExtracurricularTest.java`** e **`ExtracurricularInterfaceTest.java`**
* **`user/UserSimpleTest.java`** e **`UserComplexTest.java`**

---

## Padrões de Projeto & Boas Práticas

1. **Observer Pattern:** Implementado para a Mailbox. Quando uma equipe (`Staff`) dispara um comunicado, todos os estudantes registrados (`Listeners`) têm suas caixas atualizadas.
2. **Encapsulamento** Utilização de *Exceptions* customizadas. Qualquer inconsistência nos dados (como falha no carregamento de imagens ou senhas fracas) gera o lançamento de erros que são interceptados pelo `SceneManager` e mostrados ao usuário através de animações em tela, sem quebrar o sistema.
3. **Gerenciadores Estáticos (Loaders):** Centralização da leitura de disco para componentes visuais (`ImageLoader`, `FontLoader`, `CatalogCardLoader`), otimizando o uso de memória do JavaFX.

---

## Tecnologias Utilizadas

* **Linguagem:** Java 21 (ou superior)
* **Framework Gráfico:** JavaFX 21 & FXML
* **Persistência de Dados:** StAX (Streaming API for XML)
* **Framework de Testes:** JUnit 5
* **Ferramenta de Build:** Gradle

---

## Como compilar?

Esteja na pasta raiz do projeto;

### Linux/macOS:
Digite os comandos ./gradlew build (e espere compilar com sucesso) e depois ./gradlew run;
### Windows:
Digite os comandos gradlew build seguido de gradlew run;

e.... pronto!!! Vai compilar