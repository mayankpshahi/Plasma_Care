import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class delete extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
        PrintWriter p=response.getWriter();
        ServletContext sc=getServletContext();
        String a1;
        a1=request.getParameter("search");
      
        try
        {
       Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
        Statement st1=con.createStatement();
       int y=st.executeUpdate("delete from register where email='"+a1+"'");
       int z=st.executeUpdate("delete from request where email='"+a1+"'");
            RequestDispatcher rd=request.getRequestDispatcher("admin_view_user");
           
       if(y>0||z>0){
           rd.include(request, response);
       p.println("<center><font color='green' size='25px'><h2>Record deleted Successfully!!</h2></center>");
       
       
       }
       else{        rd.include(request, response);
       p.println("<center><font color='red' size='25px'><h2>Sorry record not deleted</h2></center>");
       }
        }
        catch(Exception ex){ex.printStackTrace();}
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
