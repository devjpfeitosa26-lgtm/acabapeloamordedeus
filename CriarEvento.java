import java.time.LocalDateTime;

public class CriarEvento {
    public Evento executar(Login.Sessao sessao, String local, String titulo, int capacidade, LocalDateTime data) {
        if (sessao == null || !sessao.getUsuario().isCriadorDeEvento()) {
            throw new IllegalArgumentException("Somente organizadores e administradores podem criar eventos.");
        }
        if (data == null || data.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Informe uma data futura para o evento.");
        }
        return new Evento(local, titulo, capacidade, data);
    }
}
