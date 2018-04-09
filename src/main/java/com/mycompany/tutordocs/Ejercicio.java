package com.mycompany.tutordocs;

/**
 *
 * @author nacho
 */
public class Ejercicio {
    private int id, id_cuestionario, correct;
    private String pregunta, optionOne, optionTwo, optionThree;

    public Ejercicio(int id, int id_cuestionario, int correct, String pregunta, String optionOne, String optionTwo, String optionThree) {
        this.id = id;
        this.id_cuestionario = id_cuestionario;
        this.correct = correct;
        this.pregunta = pregunta;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
    }

    public int getId() {
        return id;
    }

    public int getId_cuestionario() {
        return id_cuestionario;
    }

    public int getCorrect() {
        return correct;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }
    
    
}
