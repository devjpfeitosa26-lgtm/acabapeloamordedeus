public final class Permissoes {
    private final RepositorioUsuarios repositorio;

    public Permissoes(RepositorioUsuarios repositorio) {
        this.repositorio = java.util.Objects.requireNonNull(repositorio);
    }

    public Usuario promoverAdministrador(Login.Sessao sessao, String emailAlvo) {
        if (sessao == null) throw new IllegalArgumentException("Faça login primeiro.");
        Usuario responsavel = repositorio.buscarPorEmail(sessao.getUsuario().getEmail().getValor());
        if (responsavel == null || !responsavel.getTipo().podePromoverAdministrador()) {
            throw new IllegalArgumentException("Somente administradores podem promover usuários.");
        }
        Usuario alvo = repositorio.buscarPorEmail(emailAlvo);
        if (alvo == null) throw new IllegalArgumentException("Usuário não encontrado.");
        if (alvo.getTipo() == TipoUsuario.ADMINISTRADOR) {
            throw new IllegalArgumentException("Esse usuário já é administrador.");
        }
        Usuario promovido = alvo.comTipo(TipoUsuario.ADMINISTRADOR);
        repositorio.atualizar(promovido);
        return promovido;
    }
}
