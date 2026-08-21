public class Login { // Declara a classe pública Login, responsável por gerenciar a regra de autenticação do usuário

    public boolean autenticar(String emailDigitado, String senhaDigitada, Usuario usuarioDoBanco) { // Método que recebe as credenciais digitadas e o usuário salvo, retornando um booleano
        // Verifica se o e-mail e a senha informados batem com o cadastrado
        boolean emailValido = usuarioDoBanco.getEmail().getValor().equals(emailDigitado); // Compara o e-mail do banco de dados com a String digitada pelo usuário
        boolean senhaValida = usuarioDoBanco.getSenha().getValor().equals(senhaDigitada); // Compara a senha do banco de dados com a String digitada pelo usuário

        if (emailValido && senhaValida) { // Valida se ambas as credenciais correspondem exatamente aos dados do banco
            System.out.println("Login realizado com sucesso!"); // Exibe uma mensagem de sucesso no console
            return true; // Retorna verdadeiro, indicando que o acesso foi liberado
        } // Fecha o bloco do comando if

        throw new IllegalArgumentException("E-mail ou senha incorretos."); // Lança uma exceção informando a falha de autenticação caso o e-mail ou a senha estejam incorretos
    } // Fecha o bloco do método autenticar
} // Fecha a classe Login
