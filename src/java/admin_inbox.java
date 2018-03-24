import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class admin_inbox extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 PrintWriter p=response.getWriter();
    ServletContext sc=getServletContext();
    p.println("<head><style>\n"+"input[type=submit].btns{\n" +
" background-color:red; \n" +
"    border: none;\n" +
"    color: white;\n" +
"    padding: 15px 32px;\n" +
"    text-align: center;\n" +
"    text-decoration: none;\n" +
"    display: inline-block;\n" +
"    font-size: 16px;\n" +
"}"+"input[type=submit].btn{\n" +
" background-color: #4CAF50; \n" +
"    border: none;\n" +
"    color: white;\n" +
"    padding: 15px 32px;\n" +
"    text-align: center;\n" +
"    text-decoration: none;\n" +
"    display: inline-block;\n" +
"    font-size: 16px;\n" +
"}"+"input[type=text].searching{\n" +
"\n" +
"    padding: 12px 12px;\n" +
"    margin: 1px 0;\n" +
" border: 1px solid red;\n" +
"    border-radius: 1px;\n" +
"    box-sizing: border-box;\n" +

"    transition: ease-in-out, width .35s ease-in-out;\n" +
"}"+"input[type=text].searching:focus {\n" +
"\n" +
"    width: 250px;\n" +
"}"+"body {\n" +
"  background-image: url(\"Services-bg.jpg\");\n" +
"  \n" +
"  opacity:0.9;\n" +
"  font-family: 'Titillium Web', sans-serif;\n" +
"}"+"table, th {\n" +
"    padding: 5px 5px;\n" +
"    margin:1px 0;\n" +
" border: 2px solid rgba(128,0,0,0.9);\n" +
"    border-radius: 4px;\n" +
"    box-sizing: border-box;\n" +
"    transition: ease-in-out, width .35s ease-in-out;\n" +
"background-color:rgba(128,0,0,0.9);\n" +
"}"+"input[type=text].hpe {\n" +
"\n" +
"width: 100%;\n" +
"    padding: 12px 20px;\n" +
"    margin: 1px 0;\n" +
" border: 1px solid red;\n" +
"    border-radius: 2px;\n" +
"    box-sizing: border-box;\n" +
"    transition: ease-in-out, width .35s ease-in-out;\n" +
"background-color:cyan;\n" +
"}"+" a.your{\n" +
"  color: #2da1c1;\n" +
"  font-size: 25px;\n" +
"  text-decoration: none;\n" +
"  left: 252px;\n" +
"  float: left;\n" +
"\n" +
"}\n" +
"a.your:hover\n" +
"{\n" +
"color: #f90;\n" +
"text-decoration: underline;\n" +
"left: 252px;\n" +
"float: left;                 \n" +
"}"+
"          a.mine{\n" +
"  color: #2da1c1;\n" +
"  font-size: 25px;\n" +
"  text-decoration: none;\n" +
"  left: 252px;\n" +
"  float: right;\n" +
"\n" +
"}\n" +
"a.mine:hover\n" +
"{\n" +
"color: #f90;\n" +
"text-decoration: underline;\n" +
"left: 252px;\n" +
"float: right;                 \n" +
"}\n" +
"      </style> <script>\n" +
"  function preventBack(){window.history.forward();}\n" +
"  setTimeout(\"preventBack()\", 0);\n" +
"  window.onunload=function(){null};\n" +
"</script></head><body><ul class=\"tab-group\">\n" +
"       <a class=\"mine\" href=\"admin_logout\">Logout</a>\n" +"<a class='your' href='adminhome.html'>Go Back</a>"+
"          </ul><center><h1><font color='#2da1c1' size='6px'>List of All Requested Users</font></h1><table bgcolor='green' border='5'>\n" +
"<tr><th bgcolor='pink'>Email</th><th bgcolor='pink'>Blood Group</th><th bgcolor='pink'>Reason</th>\n" +
"</tr>\n");
        try{
         Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("select email,bgroup,reason from request where inbox is null");
        
       while(rs.next())
       {
p.println("<tr><td><input type='text' class='hpe' readonly='' value='"+rs.getString("email")+"'></td>\n" +
"<td><input type='text' class='hpe' readonly='' value='"+rs.getString("bgroup")+"'></td>\n" +
"<td><input type='text' class='hpe' readonly='' value='"+rs.getString("reason")+"'></td>\n" +
"</tr></center>");          
       }
            p.println("</table>"+
 "<center><form action='admin_response' method='post'><br> <font color='#2da1c1' size='6px'>Search:</font> <input type='text' class='searching' name='search'><br>"
                    + "<br><input type='submit' class='btn' value='YES' name='yes'>   <input type='submit' class='btns' value='NO' name='no'></form></center>");
           
        
        }
        catch(Exception ex){ex.printStackTrace();}
        
        p.println("<center><h1><font color='#2da1c1' size='6px'>List of All Guest Users</font></h1></center><table bgcolor='green' border='5'>\n" +
"<tr><th bgcolor='pink'>Name</th><th bgcolor='pink'>Mobile</th><th bgcolor='pink'>Blood Group</th><th bgcolor='pink'>Reason</th>\n" +
            "");
        try{
             Class.forName(sc.getInitParameter("driver"));
       Connection con=DriverManager.getConnection(sc.getInitParameter("res_url"),"root","");
       Statement st=con.createStatement();
       ResultSet rs1=st.executeQuery("select *from guest");
       
            while(rs1.next()){
               p.println("<tr><td><input type='text' class='hpe' readonly='' value='"+rs1.getString("name")+"'></td>\n" +
"<td><input type='text' class='hpe' readonly='' value='"+rs1.getString("mobile")+"'></td>\n" +
"<td><input type='text' class='hpe' readonly='' value='"+rs1.getString("bgroup")+"'></td>\n" +
"<td><input type='text' class='hpe' readonly='' value='"+rs1.getString("reason")+"'></td>\n" +                       
"</tr></center></body>");
           }
        } catch(Exception e){e.printStackTrace();}
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
