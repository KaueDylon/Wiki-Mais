package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CargoDAO extends BaseDAO {

    public void banirUsuario(int id) {

        String qry = "UPDATE usuario SET id_cargo = 3 where id_usuario = ?";

        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {

            pre.setInt(1, id);

            if (id < 0) {
                throw new IllegalArgumentException("O ID do usuário não pode ser vazio.");
            }

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao banir usuário: " + e.getMessage());
        }

    }

    public void desbanirUsuario(int id) {

        String qry = "UPDATE usuario SET id_cargo = 1 where id_usuario = ?";

        try (Connection conn = conn();
             PreparedStatement pre = conn().prepareStatement(qry)) {

            pre.setInt(1, id);

            if (id < 0) {
                throw new IllegalArgumentException("O ID do usuário não pode ser vazio.");
            }

            pre.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao desbanir usuário: " + e.getMessage());
        }

    }
}
