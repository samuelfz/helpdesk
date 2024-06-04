import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@WebServlet("/ajaxs/ajax/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter(); 
        try {
            DatabaseAction dbAction = new DatabaseAction();
            switch (action) {
                case "cadastrar_patrimonio": {
                    String colunas = "nome, descricao, data_aquisicao, valor, responsavel";
                    String tabela = "patrimonio";

                    String nome = request.getParameter("nome");
                    String descricao = request.getParameter("descricao");
                    String data_aquisicao = request.getParameter("data_aquisicao");
                    String valor = request.getParameter("valor");
                    String responsavel = request.getParameter("responsavel");
                    
                    String valores = "'"+nome+"', '"+descricao+"', '"+data_aquisicao+"', '"+valor+"', '"+responsavel+"'";

                    String result = dbAction.insert(colunas, valores, tabela);

                    request.setAttribute("valores", valores);
                    request.setAttribute("colunas", colunas);
                    request.setAttribute("tabela", tabela);
                    request.setAttribute("query", "INSERT INTO "+tabela+" ("+colunas+") VALUES ("+valores+");");
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }

                case "cadastrar_cliente": {
                    String colunas = "nome, email, telefone, endereco, data_cadastro";
                    String tabela = "clientes";

                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String telefone = request.getParameter("telefone");
                    String endereco = request.getParameter("endereco");
                    LocalDate data_cadastro = LocalDate.now();

                    String valores = "'"+nome+"', '"+email+"', '"+telefone+"', '"+endereco+"', '"+data_cadastro+"'";

                    String result = dbAction.insert(colunas, valores, tabela);

                    request.setAttribute("valores", valores);
                    request.setAttribute("colunas", colunas);
                    request.setAttribute("tabela", tabela);
                    request.setAttribute("query", "INSERT INTO "+tabela+" ("+colunas+") VALUES ("+valores+");");
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }

                case "criar_chamado": {
                    String colunas = "cliente_id, patrimonio_id, descricao, data_abertura, status, prioridade";
                    String tabela = "chamados";

                    String cliente_id = request.getParameter("cliente_id");
                    String patrimonio_id = request.getParameter("patrimonio_id");
                    String descricao = request.getParameter("descricao");
                    LocalDate data_abertura = LocalDate.now();
                    String status = request.getParameter("status");
                    String prioridade = request.getParameter("prioridade");

                    String valores = "'"+cliente_id+"', '"+patrimonio_id+"', '"+descricao+"', '"+data_abertura+"', '"+status+"', '"+prioridade+"'";

                    String result = dbAction.insert(colunas, valores, tabela);

                    request.setAttribute("valores", valores);
                    request.setAttribute("colunas", colunas);
                    request.setAttribute("tabela", tabela);
                    request.setAttribute("query", "INSERT INTO "+tabela+" ("+colunas+") VALUES ("+valores+");");
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/pagina.jsp").forward(request, response);
                    break;
                }

                case "query": {
                    String pagina = request.getParameter("pagina");
                    String result = dbAction.query("SELECT * FROM pessoa WHERE nome = 'Samuel'");

                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/"+pagina+".jsp").forward(request, response);
                    break;
                }

                case "consultar_patrimonio": {
                    List<Map<String, Object>> resultList = dbAction.select("*", "", "patrimonio");

                    request.setAttribute("resultList", resultList);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }
                case "consultar_clientes": {
                    List<Map<String, Object>> resultList = dbAction.select("*", "", "clientes");

                    request.setAttribute("resultList", resultList);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }
                case "consultar_chamados": {
                    List<Map<String, Object>> resultList = dbAction.select("*", "", "chamados");

                    request.setAttribute("resultList", resultList);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }

                case "insert": {
                    String pagina = request.getParameter("pagina");

                    String colunas = request.getParameter("colunas");
                    String valores = request.getParameter("valores");
                    String tabela = request.getParameter("tabela");
                    String result = dbAction.insert(colunas, valores, tabela);

                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/"+pagina+".jsp").forward(request, response);
                    break;
                }

                case "criar_usuario": {
                    String nome = request.getParameter("nome");
                    String idade = request.getParameter("idade");
                    String data_nascimento = request.getParameter("data_nascimento");

                    String valores = "'"+nome+"', "+idade+", '"+data_nascimento+"'";
                    String result = dbAction.insert("nome, idade, data_nascimento", valores, "pessoa");

                    request.setAttribute("result", result);
                    request.setAttribute("nome", nome);
                    request.setAttribute("data_nascimento", data_nascimento);
                    request.getRequestDispatcher("/WEB-INF/jsp/usuario_criado.jsp").forward(request, response);
                    break;
                }

                case "select": {
                    String pagina = request.getParameter("pagina");

                    String colunas = request.getParameter("colunas");
                    String condicao = request.getParameter("condicao");
                    String tabela = request.getParameter("tabela");

                    if (condicao == null) {
                        condicao = "";
                    }

                    List<Map<String, Object>> resultList = dbAction.select(colunas, condicao, tabela);
                    request.setAttribute("resultList", resultList);
                    request.getRequestDispatcher("/WEB-INF/jsp/tabela.jsp").forward(request, response);
                    break;
                }

                case "update": {
                    String pagina = request.getParameter("pagina");

                    String condicao = request.getParameter("condicao");
                    String valores = request.getParameter("valores");
                    String tabela = request.getParameter("tabela");
                    String result = dbAction.update(condicao, valores, tabela);

                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/"+pagina+".jsp").forward(request, response);
                    break;
                }

                case "atualizar_senha": {
                    String nome = request.getParameter("nome");
                    String senha = request.getParameter("senha");

                    // Verificar se os parâmetros não são nulos
                    if (nome == null || senha == null) {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing 'nome', 'senha' parameter");
                        return;
                    }

                    try {
                        // Verificar se o usuário existe
                        String userList = dbAction.query("SELECT * FROM pessoa WHERE nome = '"+nome+"'");

                        if (userList.isEmpty()) {
                            // Usuário não encontrado
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                        } else {
                            // Usuário encontrado, atualizar senha
                            String valores = "senha = '" + senha + "'";
                            String result = dbAction.update("WHERE nome = '"+nome+"'", valores, "pessoa");

                            request.setAttribute("result", result);
                            request.setAttribute("nome", nome);
                            request.setAttribute("senha", senha);
                            request.getRequestDispatcher("/WEB-INF/jsp/senha_atualizada.jsp").forward(request, response);
                        }
                    } catch (SQLException e) {
                        throw new ServletException("Erro ao acessar o banco de dados", e);
                    }
                    break;
                }

                case "delete": {
                    String pagina = request.getParameter("pagina");

                    String condicao = request.getParameter("condicao");
                    String tabela = request.getParameter("tabela");
                    String result = dbAction.delete(condicao, tabela);

                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/"+pagina+".jsp").forward(request, response);
                    break;
                }

                case "login": {
                    String pagina = request.getParameter("pagina");

                    String login = request.getParameter("login");
                    String senha = request.getParameter("senha");

                    String result = dbAction.login(login, senha);
                    
                    request.setAttribute("result", result);
                    request.getRequestDispatcher("/WEB-INF/jsp/"+pagina+".jsp").forward(request, response);
                    break;
                }

                default: {
                    out.println(action);
                    out.println("Acao invalida.");
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}