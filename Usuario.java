public class Usuario { // Declara a classe pública Usuario, que representa a entidade do usuário no sistema

    private final Email email; // Atributo privado e imutável que armazena o objeto Email do usuário
    private final Senha senha; // Atributo privado e imutável que armazena o objeto Senha do usuário

    public Usuario(Email email, Senha senha) { // Construtor que recebe os Value Objects 'email' e 'senha' já validados
        this.email = email; // Atribui a instância do Email recebida ao atributo de classe 'email'
        this.senha = senha; // Atribui a instância da Senha recebida ao atributo de classe 'senha'
    } // Fecha o construtor da classe

    public Email getEmail() { return email; } // Método getter público que retorna o objeto Email do usuário
    public Senha getSenha() { return senha; } // Método getter público que retorna o objeto Senha do usuário
} // Fecha a classe Usuario
