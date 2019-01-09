package data.testcontexts;

import data.contextinterfaces.IQuestionContext;
import domain.Account;
import domain.Answer;
import domain.Question;
import enums.AnswerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizQuestionTestContext implements IQuestionContext {
    //region Fields
    private ArrayList<Question> questions = new ArrayList<Question>();
    private ArrayList<Answer> answers = new ArrayList<Answer>();
    private Random random;
    //endregion

    //region Constructor
    public QuizQuestionTestContext() {
        answers.add(new Answer(1, 1, "Harry Potter", false));
        answers.add(new Answer(2,1,"Ron Weasley", false));
        answers.add(new Answer(3,1,"Hermione Granger", false));
        answers.add(new Answer(4,1,"Albus Dumbledore", true));
        answers.add(new Answer(5,2, "Game of Thrones", true));
        answers.add(new Answer(6,2,"The Walking Dead", false));
        answers.add(new Answer(7,2,"Black Mirror", false));
        answers.add(new Answer(8,2,"Stranger Things", false));
        answers.add(new Answer(9, 3, "Bot", false));
        answers.add(new Answer(10,3,"Tandglazuur", true));
        answers.add(new Answer(11,3,"Nagel", false));
        answers.add(new Answer(12,3,"Huid", false));
        answers.add(new Answer(13, 4, "1979", false));
        answers.add(new Answer(14,4,"1980", true));
        answers.add(new Answer(15,4,"1981", false));
        answers.add(new Answer(16,4,"1985", false ));
        answers.add(new Answer(17, 5, "Grindelwald", true));
        answers.add(new Answer(18,5,"Tom Riddle", false));
        answers.add(new Answer(19,5,"Neville Longbottom", false));
        answers.add(new Answer(20,5,"Arthur Weasley", false));
        questions.add(new Question(1, "Who is the headmaster of Hogwarts?", this.answers, AnswerState.UNANSWERED));
        questions.add(new Question(2, "Which show is about the battle between fire and ice?", this.answers, AnswerState.UNANSWERED));
        questions.add(new Question(3, "What is the hardest part of the human body?", this.answers, AnswerState.UNANSWERED));
        questions.add(new Question(4, "In which year did Beatrix become queen of the Netherlands?", this.answers, AnswerState.UNANSWERED));
        questions.add(new Question(5, "Which was a love interest of Albus Dumbledore?", this.answers, AnswerState.UNANSWERED));
        questions.add(new Question(6, "When was 'The winner takes it all' of ABBA released?", this.answers, AnswerState.UNANSWERED));
    }
    //endregion

    //region Methods
    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        while(questionList.size() < 5){
            random = new Random();
            int index = random.nextInt(this.questions.size());
            Question question = this.questions.get(index);
            if (!questionList.contains(question)){
                questionList.add(question);
            }
        }
        return questionList;
    }

    //endregion
}
