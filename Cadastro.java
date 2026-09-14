public final class Cadastro {
    private final RepositorioUsuarios repositorio;

    public Cadastro(RepositorioUsuarios repositorio) {
        this.repositorio = java.util.Objects.requireNonNull(repositorio);
    }

    public Usuario cadastrar(String nome, String sobrenome, String cpf, String nascimento,
            String email, String senha, String telefone, TipoUsuario tipo) {
        if (tipo != TipoUsuario.PARTICIPANTE && tipo != TipoUsuario.ORGANIZADOR) {
            throw new IllegalArgumentException("Cadastro comum aceita somente participante ou organizador.");
        }
        if (repositorio.listar().isEmpty()) {
            throw new IllegalStateException("Configure primeiro o administrador inicial.");
        }
        return criarESalvar(nome, sobrenome, cpf, nascimento, email, senha, telefone, tipo);
    }

    /** Usado somente pela configuração local inicial; nunca pelo menu público. */
    public Usuario criarAdministradorInicial(String nome, String sobrenome, String cpf, String nascimento,
            String email, String senha, String telefone) {
        if (!repositorio.listar().isEmpty()) {
            throw new IllegalStateException("Configuração inicial já realizada.");
        }
        return criarESalvar(nome, sobrenome, cpf, nascimento, email, senha, telefone, TipoUsuario.ADMINISTRADOR);
    }

    private Usuario criarESalvar(String nome, String sobrenome, String cpf, String nascimento,
            String email, String senha, String telefone, TipoUsuario tipo) {
        if (repositorio.buscarPorEmail(email) != null) throw new IllegalArgumentException("E-mail já cadastrado.");
        Usuario usuario = new Usuario(nome, sobrenome, cpf, nascimento, new Email(email),
                new Senha(senha), telefone, tipo);
        repositorio.adicionar(usuario);
        return usuario;
    }
}
