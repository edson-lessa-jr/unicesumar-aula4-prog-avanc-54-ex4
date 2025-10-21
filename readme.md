# Exercício 4: JSF - Navegação e Outcomes

**Universidade:** Unicesumar  
**Disciplina:** Programação Avançada (Java)  
**Ciclo:** 54  
**Autor (Professor):** Edson Orivaldo Lessa Junior

---

## 🎯 Objetivo do Projeto

Este projeto (Exercício 4) é um exemplo simples criado para demonstrar um conceito fundamental do **JavaServer Faces (JSF)**: como funciona a **navegação entre páginas** através de **"Outcomes" (Resultados Lógicos)**.

O objetivo é que o aluno entenda como uma ação do usuário (ex: clicar em um botão) pode, através de um método no backend (Java), direcionar o usuário para diferentes páginas `.xhtml` (ex: uma página de `sucesso.xhtml` ou `erro.xhtml`).

## 🧠 O Conceito: Navegação por Outcomes

Em JSF, a navegação de uma página para outra é controlada por "outcomes", que são simplesmente **Strings** retornadas pelos métodos de ação no seu *Backing Bean* (a classe Java que controla a página).

O fluxo funciona assim:

1.  **A Visão (View - `.xhtml`)**: O usuário está em uma página (ex: `index.xhtml`) e clica em um componente de ação, como um `<h:commandButton>`.
    ```xml
    <h:commandButton value="Login" action="#{navBean.tentarLogin}" />
    ```

2.  **O Controlador (Backing Bean - `.java`)**: O JSF invoca o método Java especificado no `action` (neste caso, `tentarLogin()` dentro da classe `NavBean.java`).

3.  **A Lógica e o "Outcome"**: O método Java executa sua lógica (ex: verifica um usuário e senha). Ao final, ele **retorna uma String**. Essa String é o "outcome" (resultado lógico).
    ```java
    // Exemplo em NavBean.java
    public String tentarLogin() {
        // ...lógica de verificação...
        if (usuarioValido) {
            return "sucesso"; // <--- Este é o OUTCOME
        } else {
            return "falha";   // <--- Este é outro OUTCOME
        }
    }
    ```

4.  **O Navegador (JSF)**: O JSF recebe essa String ("sucesso" ou "falha") e decide para qual página enviar o usuário.

## 🧭 Tipos de Navegação Demonstrados

O JSF pode decidir a próxima página de duas formas principais:

### 1. Navegação Implícita (Padrão)

Esta é a forma mais simples. O JSF pega a String do *outcome* e automaticamente procura por um arquivo `.xhtml` com **exatamente o mesmo nome**.

* Se o método retorna `"sucesso"`, o JSF procura por `sucesso.xhtml`.
* Se o método retorna `"cadastro"`, o JSF procura por `cadastro.xhtml`.
* Se o método retorna `"sobre"`, o JSF procura por `sobre.xhtml`.

Este projeto usa as páginas `sucesso.xhtml`, `erro.xhtml`, `cadastro.xhtml` e `sobre.xhtml`, sugerindo que o `NavBean.java` retorna exatamente essas strings.

### 2. Navegação Explícita (Configurada)

E se quiséssemos que o outcome `"falha"` levasse o usuário para a página `login-incorreto.xhtml`? Os nomes são diferentes.

Nesse caso, podemos definir "Regras de Navegação" (Navigation Rules) explicitamente no arquivo de configuração do JSF:
`src/main/webapp/WEB-INF/faces-config.xml`

Dentro dele, poderíamos ter algo como:

```xml
<navigation-rule>
    <from-view-id>*</from-view-id>
    
    <navigation-case>
        <from-outcome>falha</from-outcome>
        <to-view-id>/erro.xhtml</to-view-id>
    </navigation-case>
</navigation-rule>
````

## 📂 O que analisar neste projeto?

Para entender 100% o que está acontecendo, o aluno deve:

1.  **Abrir o `NavBean.java`** (em `src/main/java/br/com/exemplo/jsf/`):

    * Veja os métodos públicos que retornam `String`.
    * Quais são as *strings* que cada método pode retornar? (ex: "sucesso", "erro", etc.)

2.  **Abrir o `index.xhtml`** (em `src/main/webapp/`):

    * Veja os botões (`<h:commandButton>`) ou links (`<h:commandLink>`).
    * Qual método do `NavBean` cada botão está chamando no atributo `action`? (ex: `action="#{navBean.metodoX}"`)

3.  **Abrir o `faces-config.xml`** (em `src/main/webapp/WEB-INF/`):

    * Este arquivo está vazio ou ele contém regras de `<navigation-rule>`?
    * Se estiver vazio, o projeto usa **Navegação Implícita**.
    * Se tiver regras, o projeto usa **Navegação Explícita** (ou mista).

## 🚀 Como Executar o Projeto?

Este é um projeto [Maven](https://maven.apache.org/). Para executá-lo, você precisará de:

1.  JDK (Java Development Kit)
2.  Apache Maven
3.  Um Servidor de Aplicação Web (como Apache Tomcat, WildFly, Payara, etc.)

### Passos:

1.  Pelo terminal, navegue até a pasta raiz do projeto (onde está o `pom.xml`).
2.  Execute o comando do Maven para "empacotar" o projeto:
    ```bash
    mvn clean package
    ```
3.  Isso irá gerar um arquivo `.war` dentro da pasta `target/` (ex: `jsf-navigation-outcomes-1.0.0-SNAPSHOT.war`).
4.  Faça o "deploy" (implantação) desse arquivo `.war` no seu servidor de aplicação.
5.  Acesse o projeto no seu navegador (geralmente algo como `http://localhost:8080/nome-do-projeto/`).

<!-- end list -->
