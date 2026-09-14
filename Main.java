import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        if (args.length > 1 || (args.length == 1 && !args[0].equals("--configurar-admin"))) {
            System.out.println("Uso: Main [--configurar-admin]");
            return;
        }
        Path pasta = Path.of("dados");
        try {
            Files.createDirectories(pasta);
            // O protótipo local usa uma única instância para evitar gravações concorrentes.
            try (FileChannel canal = FileChannel.open(pasta.resolve("aplicacao.lock"),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                 FileLock trava = canal.tryLock();
                 Scanner scanner = new Scanner(System.in)) {
                if (trava == null) {
                    System.out.println("Outra instância já está usando esta base. Feche-a primeiro.");
                    return;
                }
                RepositorioUsuarios repositorio = new RepositorioUsuariosArquivo(pasta.resolve("usuarios.properties"));
                Cadastro cadastro = new Cadastro(repositorio);
                TelaCadastro tela = new TelaCadastro(scanner, cadastro);
                if (args.length == 1) {
                    if (!repositorio.listar().isEmpty()) throw new IllegalStateException("Administrador inicial já configurado.");
                    System.out.println("=== CONFIGURAÇÃO LOCAL DO PRIMEIRO ADMINISTRADOR ===");
                    tela.executar(true);
                    System.out.println("Administrador salvo. Execute normalmente e faça login.");
                    return;
                }
                if (repositorio.listar().isEmpty()) {
                    System.out.println("Responsável pela instalação: execute com --configurar-admin uma única vez.");
                    return;
                }
                Login login = new Login(repositorio);
                Permissoes permissoes = new Permissoes(repositorio);
                List<Evento> eventos = new ArrayList<>();
                while (true) {
                    System.out.println("\n=== SISTEMA DE EVENTOS ===\n1 - Cadastrar\n2 - Entrar\n0 - Encerrar");
                    String opcao = scanner.nextLine();
                    try {
                        switch (opcao) {
                            case "1":
                                tela.executar(false);
                                System.out.println("Cadastro salvo. Use a opção Entrar.");
                                break;
                            case "2":
                                System.out.print("E-mail: ");
                                String email = scanner.nextLine();
                                System.out.print("Senha: ");
                                String senha = scanner.nextLine();
                                Login.Sessao sessao = login.autenticar(email, senha);
                                new Home(sessao, scanner, eventos, permissoes).exibirMenu();
                                break;
                            case "0": return;
                            default: System.out.println("Opção inválida.");
                        }
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
            }
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Entrada encerrada.");
        } catch (java.io.IOException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
