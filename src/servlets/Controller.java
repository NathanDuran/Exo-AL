package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@author Nate
@WebServlet(name = "Controller", urlPatterns = {"/index", "/docs/*"})
public class Controller extends HttpServlet {

    //Servlet context variable
    private ServletContext context;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().log("----------------------------------------");
        getServletContext().log("Entering Controller processRequest...");

        //Get servlet context
        context = request.getServletContext();
        
        //Get or create session
        HttpSession session = request.getSession();
        getServletContext().log("Session ID " + session.getId());
        //If session is new increase session counter
        if (session.isNew()) {
            int sessionCounter = (int) context.getAttribute("SessionCounter");
            sessionCounter++;
            context.setAttribute("SessionCounter", sessionCounter);
        }
        
        //Always Forward to main.jsp
        String mainPage = "/WEB-INF/docs/main.jsp";

        //Find requested resource
        String requestPath = request.getRequestURI().substring(request.getContextPath().length());
        getServletContext().log("Controller received a request for: " + requestPath);

        //Resource to include defaults to error
        String include = null;
        switch (requestPath) {

            case "/docs/":
                include = "home.html";
                break;

            case "/docs/home":
                include = "home.html";
                break;

            case "/docs/instructions":
                include = "instructions.html";
                break;

            case "/docs/classify":
                include = "classify.html";
                break;

            case "/docs/results":
                include = "results.html";
                break;

            case "/docs/admin":
                include = "admin.html";
                break;

            default:
                include = "404page.html";
                break;
        }
        getServletContext().log("Included resource: " + include);
        request.setAttribute("included", include);

        request.getRequestDispatcher(mainPage).forward(request, response);
        getServletContext().log("Exiting Controller processRequest...");
        getServletContext().log("----------------------------------------");
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
