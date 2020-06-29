


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginService extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(true)
        {
            if(true)
            {
                RegistrationResposeEntity userResponse = new RegistrationResposeEntity();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean userExists = validateUserRecord(username, password);
                if(userExists) {
                    userResponse.setMessage("Welcome to new Login service");
                    userResponse.setToken(JWTService.createJWT(username, Integer.parseInt(Configuration.getProperties().getProperty("TOKEN_EXPIRY_TIME"))));
                    userResponse.setUsername(username);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter printWriter = response.getWriter();
                    printWriter.println(new Gson().toJson(userResponse));
                    printWriter.flush();
                }
                else{
                    JsonObject invalidresponse = new JsonObject();
                    invalidresponse.addProperty("message", "User does not exist. Please register and try login.");
                    PrintWriter printWriter = response.getWriter();
                    printWriter.println(invalidresponse.toString());
                    printWriter.flush();
                }

            }
        }

    }

    private boolean validateUserRecord(String username, String password){
        String url = Configuration.getProperties().getProperty("JDBC_URL");
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();
            //SELECT COUNT(1) FROM authservice_users WHERE username ='prasannachereddy' AND password='assa';
            String insertQuery ="SELECT * FROM authservice_users WHERE username ='"+ username + "' AND password='"+password+"';" ;
            return stmt.executeQuery(insertQuery).next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}