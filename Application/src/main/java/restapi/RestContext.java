package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domain.Playblock;
import domain.Player;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import restapi.responses.AuthenticationResponse;
import restapi.responses.QuestionResponse;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestContext {
    //region Fields
    private final String LOGINURL = "http://localhost:8090/authenticate/login";
    private final String REGISTERURL = "http://localhost:8090/authenticate/register";
    private final String QUESTIONLISTURL = "http://localhost:8090/questions/getQuestions";
    private Gson gson = new Gson();
    //endregion

    //region Methods
    public AuthenticationResponse loginUserRest(Login login) throws Exception {
        URL url = new URL(this.LOGINURL);
        String input = gson.toJson(login);

        String output = this.restAPI(url, input, "POST");
        return gson.fromJson(output, AuthenticationResponse.class);
    }

    public AuthenticationResponse registerUserRest(Register register) throws Exception {
        URL url = new URL(this.REGISTERURL);
        String input = gson.toJson(register);

        String output = this.restAPI(url, input, "POST");
        return gson.fromJson(output, AuthenticationResponse.class);
    }


    public QuestionResponse executeQueryGet() {

        // Build the query for the REST service
        System.out.println("[Query Get] : " + QUESTIONLISTURL);

        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(QUESTIONLISTURL);
        return executeHttpUriRequest(httpGet);
    }

    private QuestionResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {
        QuestionResponse questionResponse = new QuestionResponse();
        final Gson gson = new Gson();
        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            questionResponse = gson.fromJson(entityString, QuestionResponse.class);
        } catch (IOException e) {
            System.out.println("IOException : " + e.toString());
        } catch (JsonSyntaxException e) {
            System.out.println("JsonSyntaxException : " + e.toString());
        }

        return questionResponse;
    }

    private String restAPI(URL url, String input, String message) {
        String output = "";
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(message);
            connection.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);

            OutputStream outputStream = connection.getOutputStream();
            if(!input.equals("")) {
                outputStream.write(input.getBytes());
            }
            outputStream.flush();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: HTTP error code is " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                output += line;
            }

            connection.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return output;
    }
    //endregion


}
