package logic.repositories;

import data.mssqlcontexts.QuizQuestionMSSQLContext;
import data.testcontexts.QuizAuthenticationTestContext;
import data.testcontexts.QuizQuestionTestContext;
import domain.Account;
import domain.Playblock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restapi.viewmodels.Login;
import restapi.viewmodels.Register;

public class QuestionRepositoryTest {
    private QuestionRepository questionRepository;

    @Before
    public void initialize() {
        this.questionRepository = new QuestionRepository(new QuizQuestionMSSQLContext());
    }

    @Test
    public void testGetQuestions() {
        Playblock playblock = new Playblock(this.questionRepository.getQuestions());
        Assert.assertEquals(playblock.getQuestions().size(), 5);
    }
}
