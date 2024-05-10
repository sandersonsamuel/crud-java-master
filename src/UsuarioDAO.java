import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarUsuario(Usuario Usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, Usuario.getNome());
            pstmt.setString(2, Usuario.getEmail());
            pstmt.executeUpdate();
        }
    }

    public List<Usuario> lerUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM usuarios";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                usuarios.add(new Usuario(id, nome, email));
            }
        }
        return usuarios;
    }


    public void atualizarUsuario(Usuario Usuario) throws SQLException {
        String sql = "UPDATE usuarios SET email=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, Usuario.getEmail());
            pstmt.setInt(2, Usuario.getId());
            pstmt.executeUpdate();
        }
    }

    public void excluirUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
