public class Usuario {
    private final Email email;
    private final Senha senha;
    private final TipoUsuario tipo;

    public Usuario(Email email, Senha senha, TipoUsuario tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo de usuário é obrigatório.");
        }

        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Email getEmail() { return email; }
    public Senha getSenha() { return senha; }
    public TipoUsuario getTipo() { return tipo; }

    public boolean isCriadorDeEvento() {
        return tipo == TipoUsuario.CRIADOR_EVENTO;
    }
}
