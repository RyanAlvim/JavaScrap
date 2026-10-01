# ☕ JavaScrap

<p align="center">
  <strong>Automação de coleta de produtos e preços com Java e Selenium WebDriver.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Swing-Desktop-4479A1?style=for-the-badge" alt="Java Swing">
</p>

---

## 📖 Sobre o projeto

O **JavaScrap** é uma aplicação desktop desenvolvida em Java para automatizar a coleta de informações de produtos disponibilizados no site da ZapGráfica.

Utilizando o Selenium WebDriver, a aplicação navega pelas categorias da loja, identifica links de produtos e acessa suas páginas para extrair informações comerciais e técnicas.

O sistema também percorre as opções de quantidade disponíveis para coletar preços e relacioná-los às respectivas unidades. Ao final do processamento, os dados são organizados em um arquivo CSV.

A aplicação possui uma interface gráfica em Java Swing para entrada de credenciais e inicialização do processo.

## ✨ Funcionalidades

* 🖥️ Interface desktop com Java Swing.
* 🔐 Formulário para entrada de e-mail e senha.
* 🗂️ Leitura de categorias a partir de arquivo de configuração.
* 🌐 Navegação automatizada com Selenium WebDriver.
* 🔎 Identificação e coleta de links de produtos.
* ♻️ Evita adicionar links repetidos durante a coleta de categorias.
* 📦 Extração de características dos produtos.
* 🖼️ Coleta de URLs de imagens.
* 💲 Leitura de preços para diferentes opções de quantidade.
* 📄 Identificação de links para arquivos de gabarito ZIP, quando disponíveis.
* 📊 Exportação dos resultados para CSV.

## ⚙️ Fluxo de funcionamento

```
┌──────────────────────────┐
│ Inicialização da aplicação│
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Interface de login       │
│ Java Swing                │
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Leitura das categorias   │
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Navegação com Selenium   │
│ e coleta de links        │
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Acesso às páginas        │
│ dos produtos             │
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Extração de características│
│ imagens e preços         │
└─────────────┬────────────┘
              ↓
┌──────────────────────────┐
│ Exportação para CSV      │
└──────────────────────────┘
```

## 🛠️ Tecnologias utilizadas

| Tecnologia                   | Aplicação                                         |
| ---------------------------- | ------------------------------------------------- |
| **Java**                     | Lógica de automação e processamento dos dados     |
| **Selenium WebDriver 4.1.2** | Controle do navegador e interação com páginas web |
| **Java Swing**               | Interface gráfica e formulário de autenticação    |
| **Java AWT**                 | Componentes e eventos da interface                |
| **Maven**                    | Gerenciamento de dependências                     |
| **CSV**                      | Armazenamento tabular dos dados coletados         |
| **Arquivos TXT**             | Entrada de categorias, links e dados auxiliares   |

## 📦 Dados coletados

O arquivo CSV é estruturado com as seguintes colunas:

| Campo           | Descrição                             |
| --------------- | ------------------------------------- |
| `referencia`    | Referência do produto                 |
| `nome`          | Nome do produto                       |
| `preco`         | Preço associado à opção de quantidade |
| `quantidade`    | Opção de quantidade selecionada       |
| `material`      | Papel ou material                     |
| `cores`         | Informações de cores                  |
| `gramatura`     | Gramatura do produto                  |
| `tamanho_arte`  | Tamanho da arte                       |
| `tamanho_final` | Tamanho final                         |
| `peso`          | Peso informado                        |
| `imagem`        | URL da imagem do produto              |
| `gabarito`      | URL do gabarito, quando encontrado    |

## 📁 Estrutura do projeto

```
JavaScrap/
├── src/
│   └── main/
│       └── java/
│           ├── Main/
│           │   └── Main.java
│           ├── Login/
│           │   └── Logar.java
│           ├── Categorias/
│           │   └── Categorias.java
│           ├── Config/
│           │   └── Configs.java
│           └── ZapScrap/
│               └── DadosProdutos.java
├── categorias.txt
├── produtos.txt
├── config.txt
├── configUnidades.txt
├── dados.txt
├── pom.xml
└── README.md
```

### Responsabilidade das classes

**`Main`**

* Inicializa a aplicação.
* Prepara o arquivo de produtos.
* Abre a janela principal de autenticação.

**`Logar`**

* Implementa a interface gráfica de login.
* Recebe e-mail e senha.
* Permite limpar os campos e alternar a visualização da senha.
* Inicia o fluxo de coleta ao acionar o botão de login.

**`Categorias`**

* Lê as categorias configuradas.
* Acessa as páginas correspondentes.
* Identifica links de produtos.
* Registra os links encontrados para processamento posterior.

**`Configs`**

* Centraliza os caminhos dos arquivos utilizados pela aplicação.

**`DadosProdutos`**

* Realiza a autenticação automatizada.
* Acessa as páginas dos produtos.
* Extrai informações técnicas, imagens e links de gabaritos.
* Percorre as opções de quantidade para coletar preços.
* Registra os resultados no arquivo CSV.

## ▶️ Como executar

### Pré-requisitos

* JDK instalado.
* Maven.
* Google Chrome.
* ChromeDriver compatível com a versão do navegador.
* Acesso autorizado ao site consultado.

### Instalação

Clone o repositório:

```
git clone https://github.com/RyanAlvim/JavaScrap.git
```

Acesse a pasta:

```
cd JavaScrap
```

Compile o projeto:

```
mvn clean package
```

O projeto foi originalmente desenvolvido com Selenium 4.1.2 e contém uma configuração de ChromeDriver voltada ao Windows. A execução em outros sistemas operacionais pode exigir ajustes no caminho do driver e nos caminhos dos arquivos de configuração.

A compilação não garante, por si só, que a automação funcionará: o navegador, os seletores, a autenticação e a estrutura atual do site também precisam ser compatíveis.

## 🔐 Segurança e configuração

O projeto recebe credenciais por meio da interface gráfica. Para uma versão mais segura e fácil de manter, recomenda-se:

* Não armazenar senhas em arquivos versionados.
* Evitar registrar credenciais em logs.
* Utilizar mecanismos seguros de configuração.
* Não publicar dados comerciais ou pessoais obtidos sem autorização.
* Respeitar os termos de uso e as políticas do site consultado.

## 🔧 Melhorias futuras

* [ ] Substituir esperas fixas por esperas explícitas do Selenium.
* [ ] Separar a interface gráfica da lógica de automação.
* [ ] Utilizar `try-with-resources` para o gerenciamento de arquivos.
* [ ] Garantir o encerramento do WebDriver em caso de falha.
* [ ] Melhorar o tratamento de alterações no HTML.
* [ ] Validar os dados antes de gravá-los no CSV.
* [ ] Permitir configurar caminhos sem depender do sistema operacional.
* [ ] Atualizar o gerenciamento do ChromeDriver.
* [ ] Adicionar testes automatizados para o processamento dos dados.
* [ ] Implementar tratamento mais robusto de autenticação e erros.

## 🎯 Conceitos aplicados

O JavaScrap explora conceitos práticos de desenvolvimento de software:

* Programação orientada a objetos.
* Desenvolvimento de interfaces gráficas.
* Automação de navegadores.
* Web scraping.
* Manipulação do DOM por meio do Selenium.
* Leitura e escrita de arquivos.
* Extração e transformação de dados.
* Exportação de informações para CSV.
* Integração entre interface, automação e persistência em arquivos.

## 📌 Estado do projeto

Projeto de automação desenvolvido para fins de aprendizado e aplicação prática de Java e Selenium WebDriver.

Seu funcionamento depende da estrutura das páginas consultadas, dos mecanismos de autenticação e da compatibilidade do ambiente de execução.

## 👨‍💻 Autor

**Ryan Rodrigues Alvim**

* GitHub: [@RyanAlvim](https://github.com/RyanAlvim)

---

<p align="center">
  <i>Java, automação web e transformação de dados em informação estruturada.</i>
</p>
