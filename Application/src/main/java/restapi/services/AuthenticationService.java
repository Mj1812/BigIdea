package restapi.services;

import com.google.gson.Gson;
import data.mssqlcontexts.QuizAuthenticationMSSQLContext;
import domain.Account;
import logic.repositories.AuthenticationRepository;
import restapi.responses.AuthenticationResponse;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;

import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authenticate")
public class AuthenticationService {
        //region Fields
        private AuthenticationRepository repository = new AuthenticationRepository(new QuizAuthenticationMSSQLContext());
        private Account account;
        private AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        //endregion



        //region Methods
        @POST
        @Path("/login")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response loginUser(Login login) {
            this.account = repository.loginAccount(login);
            if(this.account != null) {
                this.authenticationResponse.setUserId(account.getId());
                this.authenticationResponse.setUsername(account.getName());
            } else {
                this.authenticationResponse.setUserId(-1);
                this.authenticationResponse.setMessage("Username and password do not match.");
            }

            Gson gson = new Gson();
            String output = gson.toJson(this.authenticationResponse);
            return Response.status(200).entity(output).build();
        }

        @POST
        @Path("/register")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response registerUser(Register register) {
            this.account = repository.registerAccount(register);
            if(this.account != null) {
                this.authenticationResponse.setUserId(account.getId());
                this.authenticationResponse.setUsername(register.getUsername());
            } else {
                this.authenticationResponse.setUserId(-1);
                this.authenticationResponse.setMessage("Could not create a new account. Please try again later.");
            }

            Gson gson = new Gson();
            String output = gson.toJson(this.authenticationResponse);
            return Response.status(200).entity(output).build();
        }
        //endregion
}
