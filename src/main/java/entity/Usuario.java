package entity;

public class Usuario {

    private Long id;
    private int cargo;
    private String nome;
    private String email;
    private String criacao;

    public Usuario(Long id, int cargo, String nome, String email, String criacao) {
        this.id = id;
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.criacao = criacao;
    }

    public Usuario(int cargo, String nome, String email, String criacao) {
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.criacao = criacao;
    }

    public Long getId() {
        return id;
    }

    public int getCargo() {
        return cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCriacao() {
        return criacao;
    }
}
