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
import javax.servlet.http.HttpSession;
public class change_pass extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession hs=request.getSession();
        PrintWriter p=response.getWriter();
        
        ServletContext sc=getServletContext();
        String a1,a2,a3;
        int y;
        query_details qu=new query_details();
        a1=request.getParameter("newpass");
        a2=request.getParameter("rnewpass");
        a3=hs.getAttribute("email").toString();
        try{
                 Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
           Statement st=con.createStatement();
           RequestDispatcher rd=request.getRequestDispatcher("/login.html");

           if(a1.equals(a2)){
           y=st.executeUpdate(qu.update_pass(a3, a1));
           if(y>0){
               hs.invalidate();
               rd.include(request, response);
               p.println("<font color='green' size='50px'><center>Password Changed Successfully!!</center></font>");
               
           }
           else{
               RequestDispatcher rd1=request.getRequestDispatcher("/change_pass.html");
           rd1.include(request, response);
           p.println("<font color='red' size='50px'><center>Password not Changed</center></font>");
           }
           
           }
           else{
               RequestDispatcher rd2=request.getRequestDispatcher("/change_pass.html");
               rd2.include(request, response);
            p.println("<font color='red' size='50px'> <center>Both Passwords not matched</center></font>");
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
