public final class Login {
    private final RepositorioUsuarios repositorio;

    public Login(RepositorioUsuarios repositorio) {
        this.repositorio = java.util.Objects.requireNonNull(repositorio);
    }

    public Sessao autenticar(String email, String senha) {
        Usuario usuario;
        try {
            usuario = repositorio.buscarPorEmail(email);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }
        if (usuario == null || !usuario.getSenha().confere(senha)) {
            throw new IllegalArgumentException("E-mail ou senha incorretos.");
        }
        return new Sessao(usuario);
    }

    /** Só Login cria uma sessão, depois de conferir as credenciais. */
    public static final class Sessao {
        private final Usuario usuario;
        private boolean ativa = true;
        private Sessao(Usuario usuario) { this.usuario = usuario; }
        public Usuario getUsuario() {
            if (!ativa) throw new IllegalStateException("Sessão encerrada. Entre novamente.");
            return usuario;
        }
        public void encerrar() { ativa = false; }
    }
}
