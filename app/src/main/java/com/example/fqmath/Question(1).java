package com.example.fqmath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {

    private String question;
    private String [] answerArray;
    private String answer;

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String value)
    {
        question = value;
    }

    public String[] getAnswerArray()
    {
        return answerArray;
    }

    public void setAnswerArray(String[] value)
    {
        answerArray = new String [value.length];
        for(int i=0; i<value.length; i++)
        {
            answerArray[i] = value[i];
        }
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //Costruttore di default per l'oggetto domanda
    public Question()
    {
        setQuestion("");
        setAnswerArray(new String [4]);
        setAnswer("");
    }

    //Costruttore per l'oggetto domanda, il primo parametro Ã¨ la domanda ed il secondo l'array delle risposte
    public Question(String question, String [] answerArray)
    {
        setQuestion(question);
        setAnswerArray(answerArray);
        setAnswer("");

        //La risposta corretta viene sempre messa in posizione zero
        this.answer = answerArray[0];

        //Il vettore viene mescolato
        this.answerArray = shuffleArray(this.answerArray);
    }

    //Funzione per mescolare il vettore casualmente
    private String [] shuffleArray(String[] array)
    {
        int index;
        String temp;
        Random rnd = new Random();

        for(int i=array.length - 1; i>0; i--)
        {
            index = rnd.nextInt(i+1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
}