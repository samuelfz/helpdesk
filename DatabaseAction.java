import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseAction {
    public String login(String usuario, String senha) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        ResultSet rsUpdate = null;
        try {
            Connection conn = DatabaseConnection.getConnection(); // Implemente o método getConnection() ou obtenha a conexão de alguma outra forma
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            rsUpdate = pstmt.executeQuery();

            if (rsUpdate.next()) {
                return "Login bem-sucedido"; // ou algum outro indicador de sucesso
            } else {
                return "Usuário ou senha incorretos"; // ou algum outro indicador de falha
            }

        } catch (SQLException e) {
            // Trate exceções adequadamente
            e.printStackTrace(); // Ou registre a exceção em um log
            return "Erro ao realizar login";
        } finally {
            // Certifique-se de fechar os recursos
            if (rsUpdate != null) {
                try {
                    rsUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String query(String query) throws SQLException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            ResultSet rsResult = stmt.executeQuery();

            StringBuilder result = new StringBuilder();
            ResultSetMetaData rsmd = rsResult.getMetaData();
            int numColunas = rsmd.getColumnCount();

            while (rsResult.next()) {
                for (int i = 1; i <= numColunas; i++) {
                    result.append(rsResult.getString(i));
                    if (i < numColunas) {
                        result.append(", ");
                    }
                }
                result.append("\n");
            }
         return result.toString();

        } catch(SQLException e) {
            e.printStackTrace();
            return "Erro ao executar a query: "+e.getMessage();
        }
    }

    public String insert(String colunas, String valores, String tabela) throws SQLException {
        String query = "INSERT INTO "+tabela+" ("+colunas+") VALUES ("+valores+");";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            return "Insert concluido com sucesso.";
        } catch(SQLException e) {
            e.printStackTrace();
            return "Erro ao executar a query: "+e.getMessage();
        }
    }

    public List<Map<String, Object>> select(String colunas, String condicao, String tabela) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String query = "SELECT " + colunas + " FROM "+tabela+" " + condicao;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rsResult = stmt.executeQuery()) {

            ResultSetMetaData metaData = rsResult.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rsResult.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rsResult.getObject(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public String update(String condicao, String valores, String tabela) throws SQLException {
        String query = "UPDATE "+tabela+" SET "+valores+" WHERE "+condicao;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            return "Update concluido com sucesso!";
        } catch(SQLException e) {
            e.printStackTrace();
            return "Erro ao executar a query: "+e.getMessage();
        }

    }

    public String delete(String condicao, String tabela) throws SQLException {
        String query = "DELETE FROM "+tabela+" WHERE "+condicao;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate();
            return "Delete concluido com sucesso!";
        } catch(SQLException e) {
            e.printStackTrace();
            return "Erro ao executar a query: "+e.getMessage();
        }
    }
}