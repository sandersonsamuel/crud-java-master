import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Criar usuário");
                System.out.println("2. Ler usuários");
                System.out.println("3. Atualizar usuário");
                System.out.println("4. Excluir usuário");
                System.out.println("5. Sair");
                System.out.print("Opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha após a leitura do inteiro

                switch (opcao) {
                    case 1:
                        criarUsuario(usuarioDAO, scanner);
                        break;
                    case 2:
                        lerUsuarios(usuarioDAO);
                        break;
                    case 3:
                        atualizarUsuario(usuarioDAO, scanner);
                        break;
                    case 4:
                        excluirUsuario(usuarioDAO, scanner);
                        break;
                    case 5:
                        System.out.println("Encerrando o programa.");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static void criarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) throws SQLException {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(0, nome, email);
        usuarioDAO.criarUsuario(usuario);
        System.out.println("Usuário criado com sucesso!");
    }

    private static void lerUsuarios(UsuarioDAO usuarioDAO) throws SQLException {
        List<Usuario> usuarios = usuarioDAO.lerUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail());
        }
    }

    private static void atualizarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do usuário que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha após a leitura do inteiro
        System.out.println("Digite o novo email do usuário:");
        String novoEmail = scanner.nextLine();

        Usuario usuario = new Usuario(id, null, novoEmail);
        usuarioDAO.atualizarUsuario(usuario);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void excluirUsuario(UsuarioDAO usuarioDAO, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do usuário que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha após a leitura do inteiro

        usuarioDAO.excluirUsuario(id);
        System.out.println("Usuário excluído com sucesso!");
    }
}
