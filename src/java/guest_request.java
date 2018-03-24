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

public class guest_request extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     PrintWriter p=response.getWriter();
     HttpSession hs=request.getSession();
    ServletContext sc=getServletContext();
    String x,z,m,a1,a2,a3,a4,mob,t;
    mob=request.getParameter("mobile");
    a1=request.getParameter("bg");
    a2=request.getParameter("res");
    hs.setAttribute("mobile",mob);
    a4=request.getParameter("name");
    a3=hs.getAttribute("mobile").toString();
     boolean y=false;
    
    
    x="insert into guest(name,mobile,bgroup,reason) values('"+a4+"','"+a3+"','"+a1+"','"+a2+"')";
    z="update guest set name='"+a4+"', bgroup='"+a1+"',reason='"+a2+"'where mobile='"+a3+"'";
    m="select mobile from guest where mobile='"+a3+"'";
  try{
       Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
      Statement st=con.createStatement();
       ResultSet rs=st.executeQuery(m);
       y=rs.next();
       if(y==true)
       {
           st.executeUpdate(z);
           RequestDispatcher rd1=request.getRequestDispatcher("/guest_request.html");
             rd1.include(request, response);
          p.print("<center><font color='green' size='50px'>Your request has been sent successfully.</center>");
       }
       else
       {
       st.executeUpdate(x);
       RequestDispatcher rd1=request.getRequestDispatcher("/guest_request.html");
             rd1.include(request, response);
          p.print("<center><font color='green' size='50px'>Your request has been sent successfully.</center>");
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
