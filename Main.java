import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ENTAR NO SISTEMA ===");
        
        try {
            System.out.print("E-mail: ");
            String emailInput = scanner.nextLine();

            System.out.print("Senha: ");
            String senhaInput = scanner.nextLine();

            // 1. Cria os objetos e valida os dados de uma vez
            Email email = new Email(emailInput);
            Senha senha = new Senha(senhaInput);
            Usuario usuario = new Usuario(email, senha);

            // 2. Se as validações passarem, abre a Home direto
            System.out.println("✅ Sucesso! Entrando...");
            Home home = new Home(usuario);
            home.exibirMenu();

        } catch (IllegalArgumentException e) {
            // Se o e-mail ou a senha forem inválidos, a exceção cai aqui
            System.out.println("❌ Erro ao entrar: " + e.getMessage());
        }
    }
}
