package entity;

public class Usuario {

    private int id;
    private int cargo;
    private String nome;
    private String email;
    private String senha;
    private String criacao;

    public Usuario(int id, int cargo, String nome, String email, String senha) {
        this.id = id;
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int cargo, String nome, String email, String senha) {
        this.cargo = cargo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
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

    public String getSenha(){ return senha; }

    public int setId(){ return id; }

}
