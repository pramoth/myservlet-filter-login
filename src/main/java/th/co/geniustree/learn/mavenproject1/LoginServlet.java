package th.co.geniustree.learn.mavenproject1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/cc", "/www/*"})
public class LoginServlet extends HttpServlet {
    static final String SESSION_KEY = "login_user";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //1 exiting ? nop : create new session
        //   1.1 generate cookies keys
        //   1.2 set cookies jsessionid as key and 1.1 as value
        String logedInUser = (String) session.getAttribute(SESSION_KEY);
        try (PrintWriter out = response.getWriter()) {
            if (logedInUser == null) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                Object dbPassword = TempraryUserStorage.userDatabase.get(username);
                if (password != null && password.equals(dbPassword)) {
                    session.setAttribute(SESSION_KEY, username);
                } else {
                    renderResult(out, "Login Fail");
                }

            } else {
                //already login
                renderResult(out, "Already Login");
            }

        }
    }

    private void renderResult(final PrintWriter out, String msg) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet LoginServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + msg + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
