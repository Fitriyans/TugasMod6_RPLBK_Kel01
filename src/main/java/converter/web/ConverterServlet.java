package converter.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import converter.ejb.ConverterBean;

@WebServlet(name = "ConverterServlet", urlPatterns = {"/ConverterServlet"})
public class ConverterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ConverterBean cb = new ConverterBean();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();
    // Output the results
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Temperature Converter</title>");
    out.println("<style>");
    out.println("body {font-family: Arial, sans-serif; background-color: #f0f0f0;}");
    out.println(".container {max-width: 500px; margin: 0 auto; padding: 20px; background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);}");
    out.println(".h1 {text-align: center;}");
    out.println(".form {text-align: center;}");
    out.println("input[type='text'] {width: 100%; padding: 10px; margin: 5px 0;}");
    out.println("input[type='submit'] {background-color: #007bff; color: white; padding: 10px 20px; border: none; cursor: pointer;}");
    out.println("input[type='submit']:hover {background-color: #0056b3;}");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<div class='container'>");
    out.println("<h1 class='h1'>Temperature Converter</h1>");
    try {
        String degree = request.getParameter("degree");
        if ((degree != null) && (degree.length() > 0)) {
            double d = Double.parseDouble(degree);
            if (request.getParameter("C TO R") != null) {
                String centigrade = cb.ctor(d);
                out.println("<p>" + degree + " Celsius are " + centigrade + " Reamur.</p>");
            }
            if (request.getParameter("R TO C") != null) {
                String reamur = cb.rtoc(d);
                out.println("<p>" + degree + " Reamur are " + reamur + " Celsius.</p>");
            }
        } else {
            out.println("<p>Enter degree to convert:</p>");
            out.println("<form class='form' method='get'>");
            out.println("<input type='text' name='degree' placeholder='Enter temperature' required>");
            out.println("<br/>");
            out.println("<input type='submit' name='R TO C' value='R TO C'>"
                    + "<input type='submit' name='C TO R' value='C TO R'>");
            out.println("</form>");
        }
    } finally {
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
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
