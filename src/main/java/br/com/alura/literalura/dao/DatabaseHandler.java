package br.com.alura.literalura.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {
  private static final String URL = "jdbc:postgresql://localhost:5433/${DB_NAME}";
  private static final String USER = "${DB_USER}";
  private static final String PASSWORD = "${DB_PASSWORD}";

  public void insertBook(String title, String author) {
      String sql = "INSERT INTO books (title, author, language, donwloads) VALUES (?, ?)";

      try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
           PreparedStatement pstmt = conn.prepareStatement(sql)) {

          pstmt.setString(1, title);
          pstmt.setString(2, author);

          int affectedRows = pstmt.executeUpdate();

          if (affectedRows > 0) {
              System.out.println("Livro inserido com sucesso!");
          } else {
              System.out.println("A inserção falhou.");
          }

      } catch (SQLException e) {
          System.out.println("Erro ao inserir livro: " + e.getMessage());
      }
  }

    public void deleteBook(int id) {
        // Implementation for deleting a book by ID

        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("A deleção falhou.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
}
