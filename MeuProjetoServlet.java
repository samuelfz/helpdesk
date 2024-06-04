import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeuProjetoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Meu Projeto Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bem-vindo ao Meu Projeto!</h1>");
            out.println("<p>Este é um exemplo simples de um servlet.</p>");
            out.println("</body>");
            out.println("</html>");

            request.setAttribute("mensagem", "Olá, mundo!");
            request.getRequestDispatcher("/WEB-INF/jsp/pagina.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Processar solicitações POST, se necessário
        doGet(request, response);
    }
}
