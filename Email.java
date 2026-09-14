import java.util.Locale;

public final class Email {
    private final String valor;

    public Email(String valor) {
        if (valor == null || !valor.trim().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        // Política do projeto: a conta não diferencia maiúsculas de minúsculas.
        this.valor = valor.trim().toLowerCase(Locale.ROOT);
    }

    public String getValor() { return valor; }
}
