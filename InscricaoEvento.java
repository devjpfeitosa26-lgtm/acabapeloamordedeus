public class InscricaoEvento{
    private final Usuario usuario;
    private final Evento evento;

    // construtor
    public InscricaoEvento(Usuario usuario, Evento evento){
        if(usuario == null){
            throw new IllegalArgumentException("O usuario não pode ser nulo.");
        }

        if(evento == null){
            throw new IllegalArgumentException("O evento não pode ser nulo.");
        }

        this.evento = evento;
        this.usuario = usuario;               
    }
    
    //getters
    public Usuario getUsuario(){
        return usuario;
    }

    public Evento getEvento(){
        return evento;
    }
}
