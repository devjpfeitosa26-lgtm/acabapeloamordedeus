import java.util.List;

/** Contrato de armazenamento usado por Cadastro e Login. */
public interface RepositorioUsuarios {
    List<Usuario> listar();
    void adicionar(Usuario usuario);
    void atualizar(Usuario usuario);

    default Usuario buscarPorEmail(String texto) {
        String email = new Email(texto).getValor();
        for (Usuario usuario : listar()) {
            if (usuario.getEmail().getValor().equals(email)) return usuario;
        }
        return null;
    }
}
