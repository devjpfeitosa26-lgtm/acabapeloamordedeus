
// Cadastro e autenticação por e-mail e senha, sem recuperação de senha nesta versão.

public class Senha {

    private final String valor;

    public Senha(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Senha inválida: não pode ser nula.");
        }

        // Regra: Pelo menos 12 caracteres, 1 letra maiúscula e 1 número
        String regra = "^(?=.*[A-Z])(?=.*\\d).{12,}$";

        if (!valor.matches(regra)) {
            throw new IllegalArgumentException(
                "Senha inválida: deve ter pelo menos 12 caracteres, incluindo uma letra maiúscula e um número."
            );
        }

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
