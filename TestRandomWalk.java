package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import task1.Actor.State;

import static java.lang.System.*;

public class TestRandomWalk {

	Random rand = new Random();

	public static void main(String[] args) {
		//new TestRandomWalk().test();
		
		double num = 0.9322000000000001;
		System.out.printf("%.4f", num + num);
		
	}

	private void test() {
		// testFind();
		//testInfect();
		// testRandom();
		testMove();
	}

	void testRandom() {
		int a = (int) Math.random();
		System.out.println(a);
	}

	void testFind() {
		final Board board = new Board(5, 5, 0.5, 0.5, 25);
		List<Set<Actor>> sets;

		sets = board.find();
	}

	void testInfect() {
		final Board board = new Board(5, 5, 0, 1, 25);
		List<Set<Actor>> sets;
		sets = board.find();

		for (Set<Actor> as : sets) {
			List<Actor> healthy = board.getHealthy(as);
			List<Actor> sick = board.getSick(as);
			if (!sick.isEmpty() && !healthy.isEmpty()) {
				for (Actor a : sick) {
					if (a.doInfect()) {
						board.setAll(healthy, Actor.State.SICK);
					}
				}
			}
			for( Actor a : sick) {
				if (a.doCure()) {
					a.setState(Actor.State.IMMUNE);
				}
			}
		}

		for (Actor a : board.getActors()) {
			out.println(a);
		}
	}

	void testMove() {

		int maxRow = 5;
		int maxCol = 5;
		int minRow = 0;
		int minCol = 0;
		double threshold = 0.4;

		List<Actor> actors = new ArrayList<>();

		Actor actor1 = new Actor(Actor.State.HEALTHY, 1, 1, 1, 1);
		//Actor actor2 = new Actor(Actor.State.HEALTHY, 3, 2, 1, 1);
		actors.add(actor1);
		//actors.add(actor2);

		for (Actor a : actors) {
			System.out.println(a);
			int dir = rand.nextInt(4);
			double randomNum = Math.random();
			
			if (randomNum < threshold) {
				System.out.println(randomNum);
				break;
			} else {
				System.out.println(randomNum);
				switch (dir) {
				case 0:
					if (a.getRow() < maxRow && a.getRow() >= minRow) {
						a.setRow(a.getRow() + 1);
						System.out.println(a + " " + "DOWN");
					}
					break;
				case 1:
					if (a.getRow() <= maxRow && a.getRow() > minRow) {
						a.setRow(a.getRow() - 1);
						System.out.println(a + " " + "UP");
					}
					break;
				case 2:
					if (a.getCol() < maxCol && a.getCol() >= minCol) {
						a.setCol(a.getCol() - 1);
						System.out.println(a + " " + "LEFT");
					}
					break;
				case 3:
					if (a.getCol() <= maxCol && a.getCol() > minCol) {
						a.setCol(a.getCol() + 1);
						System.out.println(a + " " + "RIGHT");
					}
					break;
				}
			}

		}
	}
}