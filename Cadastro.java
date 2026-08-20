public class Cadastro {

    public Usuario cadastrar(String emailInput, String senhaInput) {
        // Se a senha ou e-mail forem inválidos, a exceção é lançada AQUI
        Email email = new Email(emailInput);
        Senha senha = new Senha(senhaInput);

        Usuario novoUsuario = new Usuario(email, senha);
        
        // Aqui você salvaria o novoUsuario no banco de dados
        System.out.println("Usuário cadastrado com sucesso!");
        
        return novoUsuario;
    }
}
