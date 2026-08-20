public class Login {

    public boolean autenticar(String emailDigitado, String senhaDigitada, Usuario usuarioDoBanco) {
        // Verifica se o e-mail e a senha informados batem com o cadastrado
        boolean emailValido = usuarioDoBanco.getEmail().getValor().equals(emailDigitado);
        boolean senhaValida = usuarioDoBanco.getSenha().getValor().equals(senhaDigitada);

        if (emailValido && senhaValida) {
            System.out.println("Login realizado com sucesso!");
            return true;
        }

        throw new IllegalArgumentException("E-mail ou senha incorretos.");
    }
}
