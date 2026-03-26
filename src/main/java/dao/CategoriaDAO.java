package dao;

import entity.Artigo;
import entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends BaseDAO {

    public void cadastrarCategorias(Categoria categoria){
        String qry = "INSERT INTO categoria (nome_categoria) VALUES (?)";

        try(Connection conn = conn();
            PreparedStatement pre = conn().prepareStatement(qry)){

            pre.setString(1,categoria.getNome());

            if(categoria.getNome().isEmpty()){
                throw new IllegalArgumentException("O nome da categoria não pode ser vazia.");
            }

            pre.execute();

        }catch (SQLException e){
            System.out.println("Erro ao cadastrar categoria: "+e.getMessage());
        }
    }

    public List<Categoria> listarCategorias(){
        List<Categoria> listaCategorias = new ArrayList<>();
        String qry = "SELECT id_categoria, nome_categoria FROM categoria";

        try(Connection conn = conn();
            PreparedStatement pre = conn().prepareStatement(qry)){

            ResultSet resultadoQry = pre.executeQuery();


            while (resultadoQry.next()){
                Long id = resultadoQry.getLong("id_categoria");
                String nome = resultadoQry.getString("nome_categoria");

                Categoria categoria = new Categoria(id,nome);
                listaCategorias.add(categoria);
            }

        }catch (SQLException e){
            System.out.println("Erro ao cadastrar categoria: "+e.getMessage());
        }

        return listaCategorias;

    }

    public boolean buscarPorId(int id){

        String qry = "SELECT cat.id_categoria " +
                "FROM categoria cat where cat.id_categoria = ?";

        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {

            pre.setInt(1, id);

            ResultSet resultadoQry = pre.executeQuery();

            if(resultadoQry.next()) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Erro ao exibir artigo: " + e.getMessage());
        }

        return false;
    }

}
