// Cadastro e autenticação por e-mail e senha, sem recuperação de senha nesta versão.

public class Email { // Declara a classe pública Email, que representa o Value Object para o endereço de e-mail

    private final String valor; // Atributo privado e imutável (final) que armazena a String do e-mail

    public Email(String valor) { // Construtor da classe que recebe a String do e-mail como parâmetro
        // Validação simples: não pode ser nulo, vazio e precisa ter '@'
        if (valor == null || valor.isBlank() || !valor.contains("@")) { // Checa se o valor é nulo, composto só por espaços ou se não possui o caractere '@'
            throw new IllegalArgumentException("Email inválido, verifique a estrutura e tente novamente."); // Lança uma exceção de argumento inválido se qualquer condição falhar
        } // Fecha o bloco do comando if

        this.valor = valor; // Atribui a String validada ao atributo de instância 'valor'
    } // Fecha o construtor da classe

    public String getValor() { // Método público getter para obter o valor armazenado
        return valor; // Retorna a String contendo o e-mail validado
    } // Fecha o método getValor
} // Fecha a classe Email
