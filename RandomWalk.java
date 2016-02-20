package task1;

import javax.swing.*;

import task1.Actor.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * A
 */
public class RandomWalk extends JPanel implements ActionListener {

	public static void main(String[] args) {
		new RandomWalk().program();
	}

	final int width = 400; // Size of paint area
	final int height = 400;
	int step = 0;

	final static List<Double> nrOfSick = new ArrayList<>();
	final static List<Double> nrOfHealthy = new ArrayList<>();
	final static List<Double> nrOfCured = new ArrayList<>();
	final static List<Double> average = new ArrayList<>();
	public static final List<Double> pos = new ArrayList<>();

	final int averageOver = 5;
	final int N = 1000;
	final double beta = 0.5;
	final double gamma = 0.01;
	boolean stopp = false;

	/*
	 * List<Integer> A = new ArrayList<>(); List<Integer> B = new ArrayList<>();
	 */

	final Board board = new Board(100, 100, beta, gamma, N); // Call
	// constructor.
	final JButton stoppButton = new JButton("Stopp");

	void program() {
		initGraphics();
		//calcAverage();
	}

	// Called by the system
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		List<Actor> ls = board.getActors();

		for (Actor a : ls) {

			/*
			 * A.add(a.getRow()); B.add(a.getCol());
			 */

			if (a.getState() == State.HEALTHY) {
				g.setColor(Color.BLUE);
			} else if (a.getState() == State.SICK) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GREEN);
			}
			drawCircle(g2, a.getRow(), a.getCol(), 7);
		}

		board.infect();
		board.move();

		if (!board.isFinished() /* && step <= 100  && !stopp */) {
			try {

				nrOfSick.add((double) board.numberOfSick() / N);
				nrOfHealthy.add((double) board.numberOfHealthy() / N);
				nrOfCured.add((double) board.numberOfCured() / N);

				Thread.sleep(1);
				repaint();

				//System.out.println(step);
				//step++;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			/*
			 * for (int i = 0; i < (A.size() - 1); i++) { if (i == 0) {
			 * g.setColor(Color.RED); drawCircle(g2,A.get(i), B.get(i), 7); }
			 * else if (i == A.size() - 2 ) { g.setColor(Color.GREEN);
			 * drawCircle(g2,A.get(i), B.get(i), 7); } else {
			 * g.setColor(Color.BLUE); drawCircle(g2,A.get(i), B.get(i), 3); } }
			 */
			new XYLineChartExample();
		}

	}

	private void drawCircle(Graphics2D g2, double x, double y, double diam) {
		int x1 = (int) (4 * x);
		int y1 = (int) (4 * y);
		g2.fillOval((int) (x1 - diam / 2), height - (int) (y1 + diam / 2), (int) diam, (int) diam);
	}

	void initGraphics() {

		setPreferredSize(new Dimension(width, height));
		JFrame window = new JFrame(
				"Title"/*
						 * "d = " + board.threshold + " " + "beta = " + beta +
						 * " " + "gamma = " + gamma
						 */);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		/*
		 * window.add(stoppButton); stoppButton.setLocation(width, height);
		 * stoppButton.addActionListener(this);
		 */
	}

	public void actionPerformed(ActionEvent e) {
		stopp = true;
	}

	private void calcAverage() {

		int numberOfPoints = 5;
		int numberOfPoints2 = 10;
		double steplength;
		double bet;
		double gam;
		double R0;

		for (int y = 1 ; y <  numberOfPoints2; y++) {
			bet = (y*0.7);
		for (int x = 1; x < numberOfPoints; x++) {
			
			/*if ( x <= 31) {
				steplength = 1;
				gam = bet  / ( 1 + x * steplength);
			} else {
				steplength = 5;
				gam = bet / (31 + (x-31)*steplength);
			}*/
			
			steplength = 5;
			gam = bet / (33 + (x-1)*steplength);
			R0 = bet / gam;
			double averaged = 0.0;
			for (int i = 0; i < averageOver; i++) {
				board.actors.clear();
				Board board = new Board(100, 100, bet, gam, N);
				while (!board.isFinished()) {
					board.infect();
					board.move();
					nrOfCured.add((double) board.numberOfCured() / N);
				}
				averaged = averaged + nrOfCured.get(nrOfCured.size() - 1);
			}
			System.out.println(
					   bet + " " + R0 + " : " +averaged/averageOver);
		}
		}
		// pos.add(R0);
		// average.add(averaged/averageOver);
	}
	// new XYLineChartExample();

}
