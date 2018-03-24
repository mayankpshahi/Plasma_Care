import connect_query.query_details;
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

public class register extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   response.setContentType("text/html;charset=UTF-8");
   PrintWriter p =response.getWriter();
   query_details qu=new  query_details();
   int y;
   ServletContext sc=getServletContext();
   String a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
   a1=request.getParameter("fn");
   a2=request.getParameter("ln");
   a3=request.getParameter("mob");
   a4=request.getParameter("address");
   a5=request.getParameter("st");
   a6=request.getParameter("pin");
   a7=request.getParameter("em");
   a8=request.getParameter("pass");
   a9=request.getParameter("seq");
   a10=request.getParameter("ans");
   try{
       Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       y=st.executeUpdate(qu.insert_data(a1, a2, a3, a4, a5, a6, a7, a8,a9,a10));
       RequestDispatcher rd=request.getRequestDispatcher("/signup.html");
       if(y>0)
       {
            
            RequestDispatcher rd1=request.getRequestDispatcher("/login.html");
                       rd1.include(request, response);
                     p.println("<center><font color='green' size='50px'>You are registered succesfully!!!</center>");
        
       }
         else{
           
           rd.include(request, response);
           p.println("<center><font color='red' size='50px'>You are not registered try gain!!!</center>");
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
