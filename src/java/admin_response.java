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

public class admin_response extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String a2,a3;
        
       String a1=request.getParameter("search");
      PrintWriter p=response.getWriter();
        ServletContext sc=getServletContext();
      a2="update request set inbox='yes' where email='"+a1+"'";
       a3="update request set inbox='no' where email='"+a1+"'";
        try
        {
       Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       int y = 0;
       if(request.getParameter("yes")!=null)
       {
            y=st.executeUpdate(a2);
       }
           if(request.getParameter("no")!=null)
       {
            y=st.executeUpdate(a3);
       }
            RequestDispatcher rd=request.getRequestDispatcher("admin_inbox");
           
       if(y>0){
           rd.include(request, response);
       p.println("<center><font color='green'><h2>Response Sent Successfully</h2></center>");
       
       
       }
       else{        rd.include(request, response);
       p.println("<center><font color='Red'><h2>Sorry!!!</h2></center>");
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
