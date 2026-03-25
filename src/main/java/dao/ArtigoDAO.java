package dao;

import entity.Artigo;
import entity.Categoria;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            pre.setInt(2,usuario.getId());

            if(artigo.getNome().isEmpty()){
                throw new IllegalArgumentException("O nome do artigo não pode ser vazio.");
            }

            pre.setString(3, artigo.getNome());

            if(artigo.getWikitexto().isEmpty()){
                throw new IllegalArgumentException("O Wikitexto não pode ser vazio.");
            }

            pre.setString(4, artigo.getWikitexto());

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o artigo." + e.getMessage());
        }
    }

    public void consultarArtigo(){

        List<Categoria> listaCategorias = new ArrayList<>();
        String qry = "SELECT id_categoria, nome_categoria FROM categoria";

    }
}







