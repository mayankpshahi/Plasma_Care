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
public class forgotadmin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String a1,a2;
         HttpSession hs=request.getSession();
       PrintWriter p=response.getWriter();
        ServletContext sc=getServletContext();
        a1=request.getParameter("em");
         boolean y=false;
        a2="select * from admin where email='"+a1+"'";
        try{
         Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
           Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(a2);
            y=rs.next();
             if(y==true){
                 hs.setAttribute("email", a1);
                 RequestDispatcher rd=request.getRequestDispatcher("/changeadmin_pass.html");
                 rd.forward(request, response);
             }
             else{
                 RequestDispatcher rd1=request.getRequestDispatcher("/forgotadmin.html");
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
