import java.time.LocalDateTime;
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
            System.out.println("Sucesso! Entrando...");

            System.out.println("Digite o local do evento:");
            String localEvento = scanner.nextLine();

            System.out.println("Digite o titulo do evento:");
            String tituloEvento = scanner.nextLine();

            System.out.println("Digite a capacidade maxima de pessoas nesse evento:");
            int limitePessoas = scanner.nextInt();

            //System.out.println("Digite a data e hora do evento:");
            //LocalDateTime dataHoraEvento = scanner.nextLocalDateTime();

            Evento evento = new Evento(localEvento, tituloEvento, limitePessoas, null);
            Inscricao inscricao = new Inscricao(usuario, evento);

            System.out.println(inscricao.getEvento());
           // Home home = new Home(usuario);
           // home.exibirMenu();

        } catch (IllegalArgumentException e) {
            // Se o e-mail ou a senha forem inválidos, a exceção cai aqui
            System.out.println("Erro ao entrar: " + e.getMessage());
        }
    }
}
