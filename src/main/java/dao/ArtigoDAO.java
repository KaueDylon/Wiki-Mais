package dao;

import entity.Artigo;
import entity.Categoria;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtigoDAO extends BaseDAO {


    public void cadastrarArtigo(int categoria, Artigo artigo, Usuario usuario) {

        String qry = "INSERT INTO artigo (id_categoria, id_usuario, nome_artigo, wikitexto_artigo)" +
                "VALUES (?,?,?,?) ";

        try (Connection conn = conn();
             PreparedStatement pre = conn.prepareStatement(qry);
        ) {

            pre.setInt(1, categoria);
            pre.setInt(2, usuario.getId());

            if (artigo.getNome().isEmpty()) {
                throw new IllegalArgumentException("O nome do artigo não pode ser vazio.");
            }

            pre.setString(3, artigo.getNome());

            if (artigo.getWikitexto().isEmpty()) {
                throw new IllegalArgumentException("O Wikitexto não pode ser vazio.");
            }

            pre.setString(4, artigo.getWikitexto());

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o artigo." + e.getMessage());
        }
    }

    public List<Artigo> listarArtigo() {

        List<Artigo> listaArtigos = new ArrayList<>();
        String qry = "SELECT art.id_artigo, art.id_categoria, art.nome_artigo, " +
                "to_char(criacao_artigo, 'DD/MM/YYYY') as criacao_artigo_format, " +
                "art.wikitexto_artigo, usu.nome_usuario, cat.nome_categoria " +
                "FROM artigo art " +
                "left join usuario usu ON art.id_usuario  = usu.id_usuario "+
                "left join categoria cat on art.id_categoria = cat.id_categoria ";

        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {

            ResultSet resultadoQry = pre.executeQuery();

            while (resultadoQry.next()) {
                Long idArtigo = resultadoQry.getLong("id_artigo");
                String nomeArtigo = resultadoQry.getString("nome_artigo");
                String criacaoArtigo = resultadoQry.getString("criacao_artigo_format");
                String nomeUsuario = resultadoQry.getString("nome_usuario");
                String nomeCategoria = resultadoQry.getString("nome_categoria");
                String textoArtigo = resultadoQry.getString("wikitexto_artigo");

                Artigo artigoTemp = new Artigo(idArtigo,nomeArtigo,textoArtigo,
                                    nomeUsuario,nomeCategoria,criacaoArtigo);

                listaArtigos.add(artigoTemp);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar artigos: " + e.getMessage());
        }

        return listaArtigos;
    }

    public Artigo listarArtigoID(int id) {

        String qry = "SELECT art.nome_artigo, art.wikitexto_artigo " +
                     "FROM artigo art where art.id_artigo = ?";

        Artigo artigoTemp = null;
        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {

            if (id < 0) {
                throw new IllegalArgumentException("O ID do artigo não pode ser vazio.");
            }

            pre.setInt(1, id);

            ResultSet resultadoQry = pre.executeQuery();

            if(resultadoQry.next()){
                String nomeArtigo = resultadoQry.getString("nome_artigo");
                String textoArtigo = resultadoQry.getString("wikitexto_artigo");

                artigoTemp = new Artigo(nomeArtigo, textoArtigo);
            }


        } catch (SQLException e) {
            System.out.println("Erro ao exibir artigo: " + e.getMessage());
        }

        return artigoTemp;
    }

    public void editarArtigo(int id, String wikiTextoEdt){

       String qry = "UPDATE artigo SET wikitexto_artigo = ? where id_artigo = ?";

        try(Connection conn = conn();
            PreparedStatement pre = conn().prepareStatement(qry)){

            if (id < 0) {
                throw new IllegalArgumentException("O ID do artigo não pode ser vazio.");
            }

            pre.setString(1, wikiTextoEdt);

            pre.setInt(2, id);

            if (wikiTextoEdt.isEmpty()) {
                throw new IllegalArgumentException("O WikiTexto do artigo não pode ser vazio.");
            }

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao editar artigo: " + e.getMessage());
        }

    }

    // apenas ao adm
    public void deletarArtigo(int id){
        String qry = "DELETE FROM artigo WHERE id_artigo = ?";

        try(Connection conn = conn();
            PreparedStatement pre = conn().prepareStatement(qry)){

            if (id < 0) {
                throw new IllegalArgumentException("O ID do artigo não pode ser vazio.");
            }

            pre.setInt(1, id);

            int linhasDeletadas = pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar artigo: " + e.getMessage());
        }


    }
}







