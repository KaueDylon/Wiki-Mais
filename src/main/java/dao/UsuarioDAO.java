package dao;

import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO extends BaseDAO {

    public void cadastrarUsuario(Usuario usuario) {

        String qry = "INSERT INTO usuario (id_cargo,nome_usuario,email_usuario,senha_usuario) " +
                "VALUES (?,?,?,?) ;";

        try (Connection conn = conn();
             PreparedStatement pre = conn.prepareStatement(qry);
        ) {

            if(usuario.getNome().isEmpty()){
                throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
            }

            pre.setInt(1, 1);
            pre.setString(2, usuario.getNome());

            if(usuario.getEmail().isEmpty()){
                throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
            }

            pre.setString(3, usuario.getEmail());


            if(usuario.getSenha().isEmpty()){
                throw new IllegalArgumentException("A senha do usuário não pode ser vazia.");
            }

            pre.setString(4, usuario.getSenha());
            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }

    }

    public Usuario loginUsuario(String nome, String senha) {

        String qry = "SELECT * FROM usuario WHERE (nome_usuario = ?) AND (senha_usuario = ?)";

        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {
            pre.setString(1, nome);
            pre.setString(2, senha);

            ResultSet resultadoQry = pre.executeQuery();

            if (resultadoQry.next()){
                int id_usuario = resultadoQry.getInt("id_usuario");
                int cargo_usuario = resultadoQry.getInt("id_cargo");
                String nome_usuario = resultadoQry.getString("nome_usuario");
                String email_usuario = resultadoQry.getString("email_usuario");
                String senha_usuario = resultadoQry.getString("senha_usuario");


                Usuario tempUsuario = new Usuario(id_usuario,cargo_usuario,nome_usuario,email_usuario,senha_usuario);
                return tempUsuario;

            }

        } catch (SQLException e) {
            System.out.println("Erro ao logar com o usuário: " + e.getMessage());
        }
        return null;
    }
}
