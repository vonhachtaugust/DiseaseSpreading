package DiseaseSpreading;

import java.util.Random;

public class Actor {

    public enum State {
        SICK, HEALTHY, IMMUNE
    }

    private double beta;
    private double gamma;
    private State state;
    private int row;
    private int col;
    private Random rand = new Random();

    public Actor(State state, int row, int col, double beta, double gamma) {
        this.state = state;
        this.row = row;
        this.col = col;
        this.beta = beta;
        this.gamma = gamma;
    }

    public boolean doInfect() {
        return (Math.random() < beta);
    }

    public boolean doCure() {
        return (Math.random() < gamma);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "state=" + state +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}
