// Cadastro e autenticação por e-mail e senha, sem recuperação de senha nesta versão.

public class Senha { // Declara a classe pública Senha, que atua como o Value Object para gerenciar a senha

    private final String valor; // Atributo privado e imutável (final) para armazenar o texto da senha

    public Senha(String valor) { // Construtor que recebe a String contendo a senha informada
        if (valor == null) { // Checa se o valor passado por parâmetro é nulo
            throw new IllegalArgumentException("Senha inválida: não pode ser nula."); // Lança uma exceção se a senha fornecida for null
        } // Fecha o bloco do if para checagem de valor nulo

        // Regra: Pelo menos 12 caracteres, 1 letra maiúscula e 1 número
        String regra = "^(?=.*[A-Z])(?=.*\\d).{12,}$"; // Define a expressão regular (regex) com as regras de complexidade mínima da senha

        if (!valor.matches(regra)) { // Testa se a senha digitada NÃO atende a todos os critérios definidos na regex
            throw new IllegalArgumentException( // Lança uma exceção detalhada caso a validação falhe
                "Senha inválida: deve ter pelo menos 12 caracteres, incluindo uma letra maiúscula e um número."
            );
        } // Fecha o bloco do if de validação do formato

        this.valor = valor; // Atribui a senha validada ao atributo de instância 'valor'
    } // Fecha o construtor da classe

    public String getValor() { // Método público getter para obter a senha
        return valor; // Retorna a String com o conteúdo da senha validada
    } // Fecha o método getValor
} // Fecha a classe Senha
