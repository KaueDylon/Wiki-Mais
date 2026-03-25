package entity;

public class Artigo {

    private Long id;
    private String nome;
    private String nomeCategoria;
    private Long idCategoria;
    private String nomeUsuario;
    private String criacao;
    private String wikitexto;



    public Artigo(Long id, String nome, String wikitexto, String nomeUsuario, String nomeCategoria, String criacao) {
        this.id = id;
        this.nome = nome;
        this.nomeCategoria = nomeCategoria;
        this.nomeUsuario = nomeUsuario;
        this.wikitexto = wikitexto;
        this.criacao = criacao;
    }

    public Artigo(String nome, String wikitexto, String nomeUsuario, long idCategoria) {
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.nomeUsuario = nomeUsuario;
        this.wikitexto = wikitexto;
    }

    public Artigo(String nomeArtigo, String wikiTexto) {
        this.nome = nomeArtigo;
        this.wikitexto = wikiTexto;
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

    public String getNomeCategoria() { return nomeCategoria; }

    public Long getIdCategoria() { return idCategoria; }

    public String getNomeUsuario() { return nomeUsuario; }

    public String getWikitexto() { return wikitexto; }
}
