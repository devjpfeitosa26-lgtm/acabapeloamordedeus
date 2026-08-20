# 📌 Sistema de Gestão de Eventos e Certificados — POO2

Repositório voltado para o desenvolvimento do projeto da disciplina de **Programação Orientada a Objetos 2 (POO2)**. O sistema contempla cadastro de usuários, gestão de eventos, inscrições, controle de acesso, avaliações e emissão de certificados.

---

## 🛠️ Status de Desenvolvimento das Classes

Abaixo está o mapeamento dos componentes da aplicação com base na estrutura do projeto:

### 🟩 Módulo 1: Autenticação e Domínio do Usuário
- [x] **`Email.java`** (Value Object): Validação de estrutura do e-mail (presença do caractere `@` e tratamento de campos nulos/vazios).
- [x] **`Senha.java`** / **`CadastroSenha.java`** (Value Object): Validação de critérios de segurança da senha (mínimo de 12 caracteres, uso de regra com Regex, verificação de letras maiúsculas e números).
- [x] **`Usuario.java`** (Entidade): Agrupamento das regras de e-mail e senha utilizando composição de objetos de domínio.
- [/] **`Cadastro.java`**: Fluxo de criação de novos usuários com tratamento de exceções para regras de negócio inválidas.
- [/] **`Login.java`**: Fluxo de autenticação, recebendo credenciais e validando contra o usuário cadastrado.

---

### 🟧 Módulo 2: Eventos e Participação (Pendente)
- [ ] **`Evento.java`**: Representação de eventos, incluindo dados básicos (título, data, local, capacidade de inscritos).
- [ ] **`Inscricao.java`**: Associação entre `Usuario` e `Evento`, registrando a participação.
- [ ] **`Controle.java`**: Gerenciamento de acessos, presenças e fluxos de controle no sistema.
- [ ] **`Home.java`**: Interface/Dashboard principal do sistema para navegação dos usuários.

---

### 🟦 Módulo 3: Pós-Evento e Relatórios (Pendente)
- [ ] **`Avaliacao.java`**: Sistema de feedback dos participantes sobre os eventos realizados.
- [ ] **`GerarCertificado.java`**: Lógica de emissão de certificados para participantes com presença confirmada.
- [ ] **`Relatorio.java`**: Geração de relatórios consolidados do sistema (inscritos, presenças e avaliações).

---

## 💻 Tecnologias Utilizadas

- **Linguagem:** Java (Orientação a Objetos)
- **Gerenciador de Dependências:** Maven (`pom.xml`)
- **Controle de Versão:** Git / GitHub

---

## 📋 Próximos Passos (Backlog)

1. [ ] Finalizar a integração da classe `Usuario` com as telas/serviços de `Cadastro.java` e `Login.java`.
2. [ ] Modelar os atributos e métodos da classe `Evento.java`.
3. [ ] Criar o mecanismo de vinculo de inscrições em `Inscricao.java`.
4. [ ] Implementar a lógica de geração dos certificados na classe `GerarCertificado.java`.
5. [ ] Configurar dependências no `pom.xml` se houver necessidade de salvar dados ou exportar arquivos (ex: PDF de certificados).
