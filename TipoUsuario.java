/** O perfil define capacidades; não precisamos de uma subclasse por perfil. */
public enum TipoUsuario {
    ADMINISTRADOR, ORGANIZADOR, PARTICIPANTE;

    public boolean podeCriarEvento() {
        return this == ADMINISTRADOR || this == ORGANIZADOR;
    }

    public boolean podePromoverAdministrador() {
        return this == ADMINISTRADOR;
    }
}
