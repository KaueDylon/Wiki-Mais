package dao;

import entity.Artigo;
import entity.Categoria;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtigoDAO extends BaseDAO {


    public void cadastrarArtigo(int categoria, Artigo artigo, Usuario usuario) {

        String qry = "INSERT INTO artigo (id_categoria, id_usuario, nome_artigo, wikitexto_artigo)" +
                "VALUES (?,?,?,?) ";

        try (Connection conn = conn();
             PreparedStatement pre = conn.prepareStatement(qry);
        ) {

            pre.setInt(1, categoria);
            pre.setInt(2,usuario.getId());
            pre.setString(3, artigo.getNome());
            pre.setString(4, artigo.getWikitexto());

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o artigo" + e.getMessage());
        }
    }
}







