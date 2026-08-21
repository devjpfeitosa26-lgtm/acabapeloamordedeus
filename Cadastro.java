public class Cadastro { // Declara a classe pública chamada Cadastro, que gerencia o fluxo de criação de usuários

    public Usuario cadastrar(String emailInput, String senhaInput) { // Método público que recebe e-mail e senha (Strings), e retorna um objeto do tipo Usuario
        // Se a senha ou e-mail forem inválidos, a exceção é lançada AQUI
        Email email = new Email(emailInput); // Instancia o objeto Email, aplicando as validações de formato no construtor
        Senha senha = new Senha(senhaInput); // Instancia o objeto Senha, aplicando as regras de segurança no construtor

        Usuario novoUsuario = new Usuario(email, senha); // Cria o objeto Usuario utilizando os Value Objects já validados (email e senha)
        
        // Aqui você salvaria o novoUsuario no banco de dados
        System.out.println("Usuário cadastrado com sucesso!"); // Exibe uma mensagem de confirmação no console
        
        return novoUsuario; // Retorna o objeto Usuario recém-criado para quem chamou o método
    } // Fecha o bloco do método cadastrar
} // Fecha o bloco da classe Cadastro
