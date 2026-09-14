public class Usuario {

    private final String nome;
    private final String sobrenome;
    private final String cpf;
    private final String dataNascimento;
    private final String telefone;

    private final Email email;
    private final Senha senha;
    private final TipoUsuario tipo;

    public Usuario(String nome, String sobrenome, String cpf, String dataNascimento, Email email, Senha senha, String telefone, TipoUsuario tipo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do usuário é obrigatório."
            );
        }

        if (sobrenome == null || sobrenome.isBlank()) {
            throw new IllegalArgumentException(
                    "O sobrenome do usuário é obrigatório."
            );
        }

        if (cpf == null || cpf.isBlank()) { 
            throw new IllegalArgumentException(
                    "O CPF do usuário é obrigatório."
            );
        }

        if (dataNascimento == null || dataNascimento.isBlank()) {
            throw new IllegalArgumentException(
                    "A data de nascimento é obrigatória."
            );
        }

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException(
                    "O telefone do usuário é obrigatório."
            );
        }

        if (email == null) {
            throw new IllegalArgumentException(
                    "O e-mail do usuário é obrigatório."
            );
        }

        if (senha == null) {
            throw new IllegalArgumentException(
                    "A senha do usuário é obrigatória."
            );
        }

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "O tipo de usuário é obrigatório."
            );
        }

        this.nome = nome.trim();
        this.sobrenome = sobrenome.trim();
        this.cpf = cpf.trim();
        try {
            java.time.LocalDate data = java.time.LocalDate.parse(dataNascimento.trim(),
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/uuuu")
                            .withResolverStyle(java.time.format.ResolverStyle.STRICT));
            if (data.isAfter(java.time.LocalDate.now())) {
                throw new IllegalArgumentException("Nascimento não pode estar no futuro.");
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("Nascimento inválido. Use dd/MM/aaaa.");
        }
        this.dataNascimento = dataNascimento.trim();
        this.email = email;
        this.senha = senha;
        this.telefone = telefone.trim();
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public Email getEmail() {
        return email;
    }

    public Senha getSenha() {
        return senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public boolean isCriadorDeEvento() {
        return tipo.podeCriarEvento();
    }

    /** Retorna uma cópia: a alteração só é persistida pelo serviço autorizado. */
    Usuario comTipo(TipoUsuario novoTipo) {
        return new Usuario(nome, sobrenome, cpf, dataNascimento, email, senha, telefone, novoTipo);
    }
}
