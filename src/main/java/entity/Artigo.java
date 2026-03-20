package entity;

public class Artigo {

    private Long id;
    private String nome;
    private Categoria id_categoria;
    private Usuario id_usuario;
    private String criacao;

    public Artigo(Long id, String nome, Categoria id_categoria, Usuario id_usuario, String criacao) {
        this.id = id;
        this.nome = nome;
        this.id_categoria = id_categoria;
        this.id_usuario = id_usuario;
        this.criacao = criacao;
    }

    public Artigo(String nome, Categoria id_categoria, Usuario id_usuario, String criacao) {
        this.nome = nome;
        this.id_categoria = id_categoria;
        this.id_usuario = id_usuario;
        this.criacao = criacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getId_categoria() {
        return id_categoria;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public String getCriacao() {
        return criacao;
    }
}
