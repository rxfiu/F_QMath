package com.example.fqmath;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Question> questions;
    private List<Question> multiplication;
    private List<Question> equation;
    private List<Question> derivative;
    private List<Question> integral;
    private static final char integral_symbol = (char)Integer.parseInt("222B", 16);

    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int score;
    private Question currentQuestion;

    public int getNumberCorrect()
    {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect)
    {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect()
    {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect)
    {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions()
    {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions)
    {
        this.totalQuestions = totalQuestions;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        if(score < 0)
            this.score = 0;

        else
            this.score = score;

    }

    public Question getCurrentQuestion()
    {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion)
    {
        this.currentQuestion = currentQuestion;
    }

    //Costruttore per iniziare il gioco
    public Game()
    {
        numberCorrect = 0;
        numberIncorrect = 0;
        totalQuestions = 0;
        score = 0;

        questions = new ArrayList<Question>();
        multiplication = new ArrayList<>();
        equation = new ArrayList<>();
        derivative = new ArrayList<>();
        integral = new ArrayList<>();

        addMultiplication();
        addEquation();
        addDerivative();
        addIntegral();

        currentQuestion = new Question();
    }

    //Funzione per aggiungere gli oggetti alla corrispettiva lista
    public void addMultiplication()
    {
        multiplication.add(new Question("78 x 14 ", new String [] {"1092", "1039" , "1872", "1728"}));
        multiplication.add(new Question("99 x 99", new String [] {"9801", "8901" , "10000", "9108"}));
        multiplication.add(new Question("90 : 1.5", new String [] {"60", "70" , "45", "50"}));
        multiplication.add(new Question("0.1 : 0.2", new String [] {"0.5", "2" , "5", "0.01"}));
    }

    public void addEquation()
    {
        equation.add(new Question("9x - 5 = 0", new String [] {"5/9", "5", "4.5", "1"}));
        equation.add(new Question("1/2x + 3/2 = 1/4", new String [] {"-5/2", "4", "2.5", "0"}));
        equation.add(new Question("x^2 + 1 > 0", new String [] {"R", "Ø", "x < -1", "x < -1 V x > 1"}));
        equation.add(new Question("x^2 - 2x + 1 = 0", new String [] {"1", "0", "2", "Ø"}));
    }

    public void addDerivative()
    {
        derivative.add(new Question("Qual è la derivata di x^2 / 2 ?", new String [] {"x", "2", "x^2", "1/2"}));
        derivative.add(new Question("Qual è la derivata di Vx?", new String [] {"1/2Vx", "1/2xVx", "2Vx", "x"}));
        derivative.add(new Question("Qual è la derivata di 0?", new String [] {"0", "1", "non esiste", "x"}));
        derivative.add(new Question("Qual è la derivata di arctan(x)?", new String [] {"1/(1+x^2)", "1+x^2", "tan(x)", "1"}));
    }

    public void addIntegral()
    {
        integral.add(new Question("Qual è il risultato di " + integral_symbol + "x dx ?", new String [] {"(x^2)/2 + c", "1 + c", "0", "2(x^2)/3 + c"}));
        integral.add(new Question("Qual è il risultato di " + integral_symbol + "1/x dx da 2 a 4?", new String [] {"ln(2)", "2", "-1/4", "ln(1/2)"}));
        integral.add(new Question("Qual è il risultato di " + integral_symbol + "x^3 dx da -5 a 5?", new String [] {"0", "(x^4)/4", "7", "625/2"}));
        integral.add(new Question("Qual è la definizione di integrale indefinito?", new String [] {"L’insieme di tutte le primitive di una funzione f", "L'area con segno compreso tra la curva e l'asse x in un intervallo", "Il coefficiente angolare della retta tangente in un punto", "Come varia l'area sottesa al variare  del II estremo di integrazione"}));
    }

    //Funzione per creare il vettore con le domande che verranno utilizzate
    public void makeNewQuestion(int counter)
    {
        Random rnd = new Random();

        //In base al turno, verrà generata una tipologia di domande
        switch (counter)
        {
            case 0:
                currentQuestion = multiplication.get(rnd.nextInt(multiplication.size()));
                multiplication.remove(currentQuestion);
                break;
            case 1:
                currentQuestion = equation.get(rnd.nextInt(equation.size()));
                equation.remove(currentQuestion);
                break;
            case 2:
                currentQuestion = derivative.get(rnd.nextInt(derivative.size()));
                derivative.remove(currentQuestion);
                break;
            case 3:
                currentQuestion = integral.get(rnd.nextInt(integral.size()));
                integral.remove(currentQuestion);
                break;
        }
        totalQuestions++;
        questions.add(currentQuestion);
    }

    //Funzione per controllare se la risposta è corretta o sbagliata, il valore passato come parametro è quello del bottone cliccato
    public boolean checkAnswer (String submittedAnswer)
    {
        boolean isCorrect;
        if(currentQuestion.getAnswer() == submittedAnswer)
        {
            numberCorrect++;
            isCorrect = true;
        }
        else
        {
            numberIncorrect++;
            isCorrect=false;
        }

        setScore(numberCorrect * 10 - numberIncorrect * 10);
        return isCorrect;
    }
}