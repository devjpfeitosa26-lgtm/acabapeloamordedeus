import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento{
    private String localEvento;
    private String tituloEvento;
    private int capacidadeDeInscritos;
    private LocalDateTime dataHoraEvento;

    // contrutor
    public Evento(String localEvento, String tituloEvento, int capacidadeDeInscritos, LocalDateTime dataHoraEvento){
        

        if(localEvento == null || localEvento.isBlank()){
            throw new IllegalArgumentException("O local do Evento é obrigatorio.");
        }

        if(tituloEvento == null || tituloEvento.isBlank()){
            throw new IllegalArgumentException("O título do evento não pode ser vazio.");
        }

        if(dataHoraEvento == null){
            throw new IllegalArgumentException("A data e o horario são obrigatorios.");
        }

        if(capacidadeDeInscritos <= 0){
            throw new IllegalArgumentException("A capacidade do evento deve ser maior que zero.");
        }

        this.localEvento = localEvento;
        this.tituloEvento = tituloEvento;
        this.dataHoraEvento = dataHoraEvento;
        this.capacidadeDeInscritos = capacidadeDeInscritos;

    }


    public String getLocalPalestra() {
        return localEvento;
    }
    public String getTituloPalestra() {
        return tituloEvento;
    }
    public int getCapacidadeDeInscritos() {
        return capacidadeDeInscritos;
    }
    public LocalDateTime getDataHoraEvento() {
        return dataHoraEvento;
    }

    // validar disponibilidade
    // verificar se há evento naquele mesmo dia e horario
    // validar se as informações são compativeis
    
} 