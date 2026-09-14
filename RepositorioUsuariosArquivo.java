import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/** Properties fornece escape de caracteres sem bibliotecas externas. */
public final class RepositorioUsuariosArquivo implements RepositorioUsuarios {
    private final Path arquivo;

    public RepositorioUsuariosArquivo(Path arquivo) {
        this.arquivo = arquivo.toAbsolutePath();
    }

    @Override
    public List<Usuario> listar() {
        if (Files.notExists(arquivo)) return new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
            Properties p = new Properties();
            p.load(reader);
            if (!"1".equals(p.getProperty("versao"))) throw new IllegalArgumentException();
            int quantidade = Integer.parseInt(obrigatorio(p, "quantidade"));
            if (quantidade < 1) throw new IllegalArgumentException();
            List<Usuario> usuarios = new ArrayList<>();
            Set<String> emails = new HashSet<>();
            for (int i = 0; i < quantidade; i++) {
                String k = "usuario." + i + ".";
                Usuario u = new Usuario(obrigatorio(p,k+"nome"), obrigatorio(p,k+"sobrenome"),
                        obrigatorio(p,k+"cpf"), obrigatorio(p,k+"nascimento"),
                        new Email(obrigatorio(p,k+"email")),
                        Senha.doArmazenamento(obrigatorio(p,k+"senha")), obrigatorio(p,k+"telefone"),
                        TipoUsuario.valueOf(obrigatorio(p,k+"tipo")));
                if (!emails.add(u.getEmail().getValor())) throw new IllegalArgumentException();
                usuarios.add(u);
            }
            if (usuarios.stream().noneMatch(u -> u.getTipo() == TipoUsuario.ADMINISTRADOR)) {
                throw new IllegalArgumentException();
            }
            return usuarios;
        } catch (IOException | IllegalArgumentException e) {
            // Um arquivo ilegível nunca é interpretado como uma base vazia.
            throw new IllegalStateException("Falha ao ler usuários. Preserve o arquivo e verifique os dados.", e);
        }
    }

    private static String obrigatorio(Properties p, String chave) {
        String valor = p.getProperty(chave);
        if (valor == null) throw new IllegalArgumentException();
        return valor;
    }

    @Override
    public synchronized void adicionar(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário obrigatório.");
        List<Usuario> usuarios = listar();
        if (usuarios.stream().anyMatch(u -> u.getEmail().getValor().equals(usuario.getEmail().getValor()))) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        usuarios.add(usuario);
        gravar(usuarios);
    }

    @Override
    public synchronized void atualizar(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário obrigatório.");
        List<Usuario> usuarios = listar();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().getValor().equals(usuario.getEmail().getValor())) {
                usuarios.set(i, usuario);
                gravar(usuarios);
                return;
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado.");
    }

    private void gravar(List<Usuario> usuarios) {
        Properties p = new Properties();
        p.setProperty("versao", "1");
        p.setProperty("quantidade", String.valueOf(usuarios.size()));
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            String k = "usuario." + i + ".";
            p.setProperty(k+"nome", u.getNome());
            p.setProperty(k+"sobrenome", u.getSobrenome());
            p.setProperty(k+"cpf", u.getCpf());
            p.setProperty(k+"nascimento", u.getDataNascimento());
            p.setProperty(k+"email", u.getEmail().getValor());
            p.setProperty(k+"senha", u.getSenha().paraArmazenamento());
            p.setProperty(k+"telefone", u.getTelefone());
            p.setProperty(k+"tipo", u.getTipo().name());
        }
        Path temporario = null;
        try {
            Files.createDirectories(arquivo.getParent());
            temporario = Files.createTempFile(arquivo.getParent(), "usuarios-", ".tmp");
            try (Writer writer = Files.newBufferedWriter(temporario, StandardCharsets.UTF_8)) {
                p.store(writer, "Usuarios - nao editar manualmente");
            }
            // Se o sistema não suportar troca atômica, falhamos sem apagar o original.
            Files.move(temporario, arquivo, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível salvar os usuários.", e);
        } finally {
            if (temporario != null) {
                try { Files.deleteIfExists(temporario); } catch (IOException ignored) { }
            }
        }
    }
}
