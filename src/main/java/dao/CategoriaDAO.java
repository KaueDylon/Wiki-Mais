package dao;

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

        }catch (SQLException e){
            System.out.println("Erro ao cadastrar categoria: "+e.getMessage());
        }
    }

}
