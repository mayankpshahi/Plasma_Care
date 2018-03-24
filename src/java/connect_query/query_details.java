package connect_query;


public class query_details {
    public String x;
public String insert_data(String fname,String lname,String mobile,String address,String state,String pincode,String email,String pass,String seq,String sans)
{
x="insert into register values('"+fname+"','"+lname+"','"+mobile+"','"+address+"','"+state+"','"+pincode+"','"+email+"','"+pass+"','"+seq+"','"+sans+"')";

return x;}
public String login_data(String email,String pass){
x="select * from register where email='"+email+"' and pass='"+pass+"'";
    return x;
}
public String admin_login_data(String email,String pass){
x="select * from admin where email='"+email+"' and password='"+pass+"'";
    return x;
}
public String update_pass(String em,String pass){
x="update register set pass='"+pass+"' where email='"+em+"'";
return x;
}
public String updateadmin_pass(String em,String pass){
x="update admin set password='"+pass+"' where email='"+em+"'";
return x;
}
public String blood_req(String bgroup,String reason)
{
    x="select bgroup,reason from request where bgroup='"+bgroup+"' and reason='"+reason+"'";
    return x;
}
}
