package restapi.services;

import com.google.gson.Gson;
import data.mssqlcontexts.QuizQuestionMSSQLContext;
import data.testcontexts.QuizQuestionTestContext;
import domain.Question;
import logic.repositories.QuestionRepository;
import restapi.responses.QuestionResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
@Path("questions")
public class QuestionService {
    //region Fields
    private QuestionRepository repository = new QuestionRepository((new QuizQuestionMSSQLContext()));
    private ArrayList<Question> questions;
    private QuestionResponse response = new QuestionResponse();
    //endregion

    //region Methods
    @GET
    @Path("/getQuestions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions() {
        this.questions = repository.getQuestions();
        if(this.questions != null) {
            response.setQuestions(this.questions);
        } else {
            response.setQuestions(null);
            response.setMessage("There were no questions retrieved.");
        }

        Gson gson = new Gson();
        String output = gson.toJson(response);
        return Response.status(200).entity(output).build();
    }
    //endregion
}
