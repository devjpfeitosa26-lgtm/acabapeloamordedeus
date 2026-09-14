import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public final class Home {
    private final Login.Sessao sessao;
    private final Scanner scanner;
    private final List<Evento> eventos;
    private final Permissoes permissoes;

    public Home(Login.Sessao sessao, Scanner scanner, List<Evento> eventos, Permissoes permissoes) {
        this.sessao = java.util.Objects.requireNonNull(sessao);
        this.scanner = scanner;
        this.eventos = eventos;
        this.permissoes = permissoes;
    }

    public void exibirMenu() {
        try {
            while (true) {
                Usuario usuario = sessao.getUsuario();
                System.out.println("\n=== HOME: " + usuario.getNome() + " | " + usuario.getTipo() + " ===");
                if (usuario.isCriadorDeEvento()) System.out.println("1 - Criar evento");
                System.out.println("2 - Listar eventos\n3 - Sair da conta");
                if (usuario.getTipo().podePromoverAdministrador()) System.out.println("4 - Promover usuário a administrador");
                String opcao = ler("Opção: ");
                try {
                    switch (opcao) {
                        case "1": criarEvento(); break;
                        case "2": listarEventos(); break;
                        case "3": return;
                        case "4": promover(); break;
                        default: System.out.println("Opção inválida.");
                    }
                } catch (IllegalArgumentException | IllegalStateException | java.time.DateTimeException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        } finally {
            sessao.encerrar();
        }
    }

    private void criarEvento() {
        if (!sessao.getUsuario().isCriadorDeEvento()) throw new IllegalArgumentException("Acesso negado.");
        String titulo = ler("Título: ");
        String local = ler("Local: ");
        int capacidade = Integer.parseInt(ler("Capacidade: "));
        LocalDateTime data = LocalDateTime.parse(ler("Data e hora (dd/MM/aaaa HH:mm): "),
                DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT));
        eventos.add(new CriarEvento().executar(sessao, local, titulo, capacidade, data));
        System.out.println("Evento criado.");
    }

    private void listarEventos() {
        if (eventos.isEmpty()) System.out.println("Nenhum evento cadastrado.");
        for (Evento evento : eventos) {
            System.out.println(evento.getTituloPalestra() + " | " + evento.getLocalPalestra()
                    + " | " + evento.getDataHoraEvento() + " | vagas: " + evento.getCapacidadeDeInscritos());
        }
    }

    private void promover() {
        if (!sessao.getUsuario().getTipo().podePromoverAdministrador()) {
            throw new IllegalArgumentException("Acesso negado.");
        }
        String email = ler("E-mail da conta que será promovida: ");
        if (!ler("Confirma promover " + email + "? (s/n): ").equalsIgnoreCase("s")) return;
        Usuario promovido = permissoes.promoverAdministrador(sessao, email);
        System.out.println(promovido.getNome() + " agora é administrador. O novo perfil aparece no próximo login.");
    }

    private String ler(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
