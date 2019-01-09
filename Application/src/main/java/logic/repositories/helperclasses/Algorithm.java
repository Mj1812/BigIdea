package logic.repositories.helperclasses;

import domain.Answer;
import domain.Question;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {
    private int buttonOneCounter = 0;
    private int buttonTwoCounter = 0;
    private int buttonThreeCounter = 0;
    private int buttonFourCounter = 0;


    public Algorithm(int buttonOneCounter, int buttonTwoCounter, int buttonThreeCounter, int buttonFourCounter) {
        this.buttonOneCounter = buttonOneCounter;
        this.buttonTwoCounter = buttonTwoCounter;
        this.buttonThreeCounter = buttonThreeCounter;
        this.buttonFourCounter = buttonFourCounter;
    }

    public Question algorithmButtons(Question question){
        int max = Math.max(Math.max(buttonOneCounter,buttonTwoCounter),Math.max(buttonThreeCounter,buttonFourCounter));
        if(max > 0){
            Answer correctAnswer = new Answer();
                for (Answer answer: question.getAnswers()) {
                    if(answer.isCorrect()){
                        correctAnswer = answer;
                    }
                }
                question.getAnswers().remove(correctAnswer);
                question.getAnswers().add(newIndex(max), correctAnswer);

            return question;
        }else{
            return question;
        }
    }

    private int newIndex(int max){
        int index = -1;
        if(max == buttonOneCounter || max == buttonTwoCounter){
            index = 3;
        } else if(max == buttonThreeCounter || max == buttonFourCounter){
            index = 0;
        }
        return index;
    }
}
