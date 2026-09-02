import java.time.LocalDateTime;

public class CriarEvento {

    public Evento executar(String localEvento, String tituloEvento, int capacidadeDeInscritos, LocalDateTime dataHoraEvento) {

        if (dataHoraEvento == null) {
            throw new IllegalArgumentException("A data e o horário são obrigatórios.");
        }

        if (dataHoraEvento.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data do evento não pode estar no passado.");
        }

        return new Evento(localEvento, tituloEvento, capacidadeDeInscritos, dataHoraEvento);
    }
}
