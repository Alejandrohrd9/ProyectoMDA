package com.mycompany.tutordocs;

/**
 *
 * @author nacho
 */
public class Exercise {

    private int id, id_cuestionario, correct, puntuacion;
    private String pregunta, optionOne, optionTwo, optionThree;

    public Exercise(int id, int id_cuestionario, int correct, String pregunta, String optionOne, String optionTwo, String optionThree, int puntuacion) {
        this.id = id;
        this.id_cuestionario = id_cuestionario;
        this.correct = correct;
        this.pregunta = pregunta;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.puntuacion = puntuacion;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

}
