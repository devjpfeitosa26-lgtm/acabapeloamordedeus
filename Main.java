import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ENTRAR NO SISTEMA ===");

        try {
            System.out.print("E-mail: ");
            String emailInput = scanner.nextLine();

            System.out.print("Senha: ");
            String senhaInput = scanner.nextLine();

            Email email = new Email(emailInput);
            Senha senha = new Senha(senhaInput);
            Usuario usuario = new Usuario(email, senha);

            System.out.println("Sucesso! Entrando...");
            Home home = new Home(usuario);
            home.exibirMenu();

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao entrar: " + e.getMessage());
        }
    }
}
