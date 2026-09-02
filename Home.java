import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Home {

    private final Usuario usuario;
    private final List<Evento> eventos = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Home(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        this.usuario = usuario;
    }

    public void exibirMenu() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Criar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    criarEvento();
                    break;
                case "2":
                    listarEventos();
                    break;
                case "3":
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println(" Opção inválida.");
            }
        }
    }

    private void criarEvento() {
        try {
            System.out.print("Título do evento: ");
            String tituloEvento = scanner.nextLine();

            System.out.print("Local do evento: ");
            String localEvento = scanner.nextLine();

            System.out.print("Capacidade de inscritos: ");
            int capacidade = Integer.parseInt(scanner.nextLine());

            System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
            String dataInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHoraEvento = LocalDateTime.parse(dataInput, formatter);

            CriarEvento criarEvento = new CriarEvento();
            Evento evento = criarEvento.executar(localEvento, tituloEvento, capacidade, dataHoraEvento);

            eventos.add(evento);
            System.out.println("Evento \"" + evento.getTituloPalestra() + "\" criado com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println(" Data ou hora em formato inválido. Use dd/MM/yyyy HH:mm.");
        } catch (NumberFormatException e) {
            System.out.println(" Capacidade deve ser um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    private void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado ainda.");
            return;
        }

        System.out.println("\n=== EVENTOS CADASTRADOS ===");
        for (Evento evento : eventos) {
            System.out.println("- " + evento.getTituloPalestra()
                    + " | " + evento.getLocalPalestra()
                    + " | vagas: " + evento.getCapacidadeDeInscritos()
                    + " | " + evento.getDataHoraEvento());
        }
    }
}
