public class Usuario {

    private String email; // puxa a classe email
    private String senha; // puxa a classe senha

    public Usuario(String email, String senha) { //cria um vetor para fazer a verificação do usuário
        if (email == null || email.isBlank()) { // condição se o campo de email está vazio ou em branco
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (senha == null || senha.length() 12) { //condição para a senha ter mínimo 12 caracteres 
            throw new IllegalArgumentException("Senha deve ter pelo menos 12 caracteres");
        }
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
