



import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;

public class SecuredGreetingService extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authToken = request.getHeader("authorization").substring(7);
        String tokenStatus = JWTService.decodeJWT(authToken);
        PrintWriter printWriter = response.getWriter();
        if(tokenStatus.equalsIgnoreCase("valid")) {
            JsonObject validresponse = new JsonObject();
            validresponse.addProperty("message", "Welcome to Authenticated API");
            validresponse.addProperty("JWT Response: ", tokenStatus);
            printWriter.println(validresponse.toString());
        }
        else
        {
            JsonObject invalidResponse = new JsonObject();
            invalidResponse.addProperty("message", "You need to be Authenticated first");
            invalidResponse.addProperty("JWT Response: ", tokenStatus);
            printWriter.println(invalidResponse.toString());
        }
        printWriter.flush();
    }
}
