


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;

public class GreetingService extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        JsonObject validresponse = new JsonObject();
        validresponse.addProperty("message", "Welcome to API");
        printWriter.println(validresponse.toString());
        printWriter.flush();
    }
}
