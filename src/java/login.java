import connect_query.query_details;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet
{
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        query_details qu=new query_details();
        HttpSession hs=request.getSession();
      
        String a1,a2;
        a1=request.getParameter("em");
        a2=request.getParameter("pass");
       boolean y=false;
       PrintWriter p=response.getWriter();
        ServletContext sc=getServletContext();
        hs.setAttribute("remail", a1);
        hs.setAttribute("pass",a2);
        try
        {
       
       
        Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery(qu.login_data(a1, a2));
            y=rs.next();
            if(y==true)
            {
                
  
             RequestDispatcher rd1=request.getRequestDispatcher("/request.html");
           rd1.include(request, response);
          
            }
            else{
                RequestDispatcher rd=request.getRequestDispatcher("login.html");
                rd.include(request, response);
            p.println("<font color='red' size='25px'>Incorrect user name and password");
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
