package entity;

public class Artigo {

    private Long id;
    private String nome;
    private int id_categoria;
    private int id_usuario;
    private String criacao;
    private String wikitexto;

    public Artigo(Long id, String nome, String wikitexto) {
        this.id = id;
        this.nome = nome;
        this.id_categoria = id_categoria;
        this.id_usuario = id_usuario;
        this.wikitexto = wikitexto;
    }

    public Artigo(String nome, String wikitexto) {
        this.nome = nome;
        this.id_categoria = id_categoria;
        this.id_usuario = id_usuario;
        this.wikitexto = wikitexto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCriacao() {
        return criacao;
    }

    public String getWikitexto() {
        return wikitexto;
    }
}
