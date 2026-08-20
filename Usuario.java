public class Usuario {
    private final Email email;
    private final Senha senha;

    public Usuario(Email email, Senha senha) {
        this.email = email;
        this.senha = senha;
    }

    public Email getEmail() { return email; }
    public Senha getSenha() { return senha; }
}
