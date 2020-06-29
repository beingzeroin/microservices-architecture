
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class RegistrationService extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // no need for this GET but adding this to make sure that api server is up
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Servlet Testing</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("Welcome to the Servlet Testing Center");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           RegistrationResposeEntity userResponse = new RegistrationResposeEntity();
            String username = request.getParameter("username"); // create constants
            String password = request.getParameter("password"); // create constants
            String email = request.getParameter("email");
            // TO-DO: move this messages to constants where u can change easily later when needed
            userResponse.setMessage("Welcome to new Authentication service");
            // TO-DO: Move this 2 min timeout to configuratio file
            userResponse.setToken(JWTService.createJWT(email, 120000));
            userResponse.setUsername(username);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if(createDBRecord(username, password, email)) {
                PrintWriter printWriter = response.getWriter();
                printWriter.println(new Gson().toJson(userResponse));
                printWriter.flush();
            }
            else
            {
                PrintWriter printWriter = response.getWriter();
                JsonObject validresponse = new JsonObject();
                validresponse.addProperty("message", "Account already exists. Please try login");
                printWriter.println(validresponse.toString());
                printWriter.flush();
            }
    }

    private boolean createDBRecord(String username, String password, String emailadderss)  {

        // handle password encryption later, for the time-being, storing in raw
        // move the url to config file - easy to change db later
        String url = Configuration.getProperties().getProperty("JDBC_URL");
        Connection conn = null;
        boolean result = false;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();
            String insertQuery = "INSERT INTO authservice_users VALUES ('" + username + "'," + "'" + password + "','" + emailadderss + "')";
            stmt.execute(insertQuery);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally{
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

    }

}