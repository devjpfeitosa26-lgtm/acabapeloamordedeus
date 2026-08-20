
// Cadastro e autenticação por e-mail e senha, sem recuperação de senha nesta versão.

public class Email {

    private final String valor;

    public Email(String valor) {
        // Validação simples: não pode ser nulo, vazio e precisa ter '@'
        if (valor == null || valor.isBlank() || !valor.contains("@")) {
            throw new IllegalArgumentException("Email inválido, verifique a estrutura e tente novamente.");
        }

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
