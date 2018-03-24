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

public class user_inbox extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession hs =request.getSession();
       PrintWriter p=response.getWriter();
    ServletContext sc=getServletContext();
   
     try{
        boolean y=false;
       Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       RequestDispatcher rd=request.getRequestDispatcher("request.html");
       ResultSet rs=st.executeQuery("select inbox from request where email='"+hs.getAttribute("remail")+"'");
     y=rs.next();
     if(y==true){
     if(rs.getString("inbox")!=null)
     {
       if(rs.getString("inbox").equals("yes"))
        {
         rd.include(request, response);
           p.println("<center><font color='blue' size='50px'><h2>*Yes Request Succeeded</h2></center>");
        }
       else if(rs.getString("inbox").equals("no"))
        {
         rd.include(request, response);
           p.println("<center><font color='blue' size='50px'><h2>*Sorry! Request Denied</h2></center>");
         }
        
     }
     else if(rs.getString("inbox")==null){
         rd.include(request, response);
           p.println("<center><font color='blue' size='50px'><h2>*No Messages Yet!</h2></center>");
         } }
     else{
         rd.include(request, response);
          p.println("<center><font color='blue' size='50px'><h2>You haven't sent any request Yet!!</h2></center>");
     }
   }
     catch(Exception ex){  ex.printStackTrace();    }
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
