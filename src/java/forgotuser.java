import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class forgotuser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String a1,a2,a3,a4;
        HttpSession hs=request.getSession();
       PrintWriter p=response.getWriter();
        ServletContext sc=getServletContext();
        a1=request.getParameter("seq");
        a2=request.getParameter("ans");
        a3=request.getParameter("em");
        boolean y=false;
        a4="select * from register where seq='"+a1+"' and sans='"+a2+"' and email='"+a3+"'";
        try{
         Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
           Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(a4);
            y=rs.next();
             if(y==true){
                 hs.setAttribute("email", a3);
                 RequestDispatcher rd=request.getRequestDispatcher("/change_pass.html");
                 rd.forward(request, response);
             }
             else{
                 RequestDispatcher rd1=request.getRequestDispatcher("/forgotuser.html");
                 rd1.include(request, response);
                  p.println("<font color='red' size='25px'>Renter Details");
             }
        }catch(Exception ex){ex.printStackTrace();}
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
