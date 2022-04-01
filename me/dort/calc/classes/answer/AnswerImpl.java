package me.dort.calc.classes.answer;

import me.dort.calc.interfaces.answer.IAnswer;


public class AnswerImpl implements IAnswer<Double> {
    private final Double answer;

    public AnswerImpl(Double answer) {
        this.answer = answer;
    }

    @Override
    public Double answer() {
        return this.answer;
    }
}
