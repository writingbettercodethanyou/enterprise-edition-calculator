package me.dort.calc.answer;

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
