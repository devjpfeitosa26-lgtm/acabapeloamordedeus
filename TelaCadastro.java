import java.util.Scanner;

/** Coleta de dados no terminal; Cadastro contém o caso de uso. */
public final class TelaCadastro {
    private final Scanner scanner;
    private final Cadastro cadastro;

    public TelaCadastro(Scanner scanner, Cadastro cadastro) {
        this.scanner = scanner;
        this.cadastro = cadastro;
    }

    public Usuario executar(boolean configuracaoInicial) {
        TipoUsuario tipo = TipoUsuario.PARTICIPANTE;
        if (!configuracaoInicial) {
            String opcao = ler("Perfil: 1 - Participante | 2 - Organizador: ");
            if (opcao.equals("2")) tipo = TipoUsuario.ORGANIZADOR;
            else if (!opcao.equals("1")) throw new IllegalArgumentException("Perfil inválido.");
        }
        String nome = ler("Nome: ");
        String sobrenome = ler("Sobrenome: ");
        String cpf = ler("CPF: ");
        String nascimento = ler("Nascimento (dd/MM/aaaa): ");
        String telefone = ler("Telefone com DDD: ");
        String email = ler("E-mail: ");
        String senha;
        while (true) {
            senha = ler("Senha (12 caracteres, maiúscula e número): ");
            if (senha.equals(ler("Confirme a senha: "))) break;
            System.out.println("As senhas não coincidem. Tente novamente.");
        }
        if (configuracaoInicial) {
            return cadastro.criarAdministradorInicial(nome, sobrenome, cpf, nascimento, email, senha, telefone);
        }
        return cadastro.cadastrar(nome, sobrenome, cpf, nascimento, email, senha, telefone, tipo);
    }

    private String ler(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }
}
