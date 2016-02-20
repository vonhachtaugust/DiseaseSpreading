package task1;

import java.util.*;

import task1.Actor.State;

/**
 *
 */

public class Board {

	private int maxRow;
	private int maxCol;
	private int minRow = 0;
	private int minCol = 0;
	private Random rand = new Random();
	public List<Actor> actors = new ArrayList<>(); // list of actors on the
	private List<Set<Actor>> sets = new ArrayList<>(); // board

	public double threshold = 0.3;

	public Board(int maxRow, int maxCol, double beta, double gamma, int nActor) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		for (int i = 0; i < nActor; i++) {
			if (i < (nActor - (int) (0.015 * nActor))) { // 0.015 for task 3 and
															// 4
				addActor(gerRandomActor(Actor.State.HEALTHY, maxRow, maxCol, beta, gamma));
			} else {
				addActor(gerRandomActor(Actor.State.SICK, maxRow, maxCol, beta, gamma));
			}
		}
	}

	public List<Set<Actor>> find() {
		for (int row = 0; row <= maxRow; row++) {
			for (int col = 0; col <= maxCol; col++) {
				Set<Actor> actorSet = new HashSet<>();
				for (Actor a : actors) {
					if (a.getRow() == row && a.getCol() == col) {
						actorSet.add(a);
					}
				}
				sets.add(actorSet);
			}
		}
		return sets;
	}

	public void infect() {
		sets.clear();
		sets = find();
		for (Set<Actor> as : sets) {
			List<Actor> healthy = getHealthy(as);
			List<Actor> sick = getSick(as);
			// if (!sick.isEmpty() && !healthy.isEmpty()) {
			for (Actor a : sick) {
				if (a.doInfect()) {
					setAll(healthy, Actor.State.SICK);
					/*
					 * if (a.doCure()) { a.setState(Actor.State.IMMUNE); }
					 * 
					 * 
					 * } else if (a.doCure()) { a.setState(Actor.State.IMMUNE);
					 * }
					 */
				}

				if (a.doCure()) {
					a.setState(Actor.State.IMMUNE);
				}

				// }
				/*
				 * for (Actor a : sick) { if (a.doCure()) {
				 * a.setState(Actor.State.IMMUNE); } }
				 */
			}
		}
	}

	public void move() {
		for (Actor a : actors) {
			step(a);
		}
	}

	public void setAll(List<Actor> as, Actor.State state) {
		for (Actor a : as) {
			a.setState(state);
		}
	}

	public List<Actor> getHealthy(Set<Actor> as) {
		List<Actor> tmp = new ArrayList<>();
		for (Actor a : as) {
			if (a.getState() == Actor.State.HEALTHY) {
				tmp.add(a);
			}
		}
		return tmp;
	}

	public List<Actor> getSick(Set<Actor> as) {
		List<Actor> tmp = new ArrayList<>();
		for (Actor a : as) {
			if (a.getState() == Actor.State.SICK) {
				tmp.add(a);
			}
		}
		return tmp;
	}

	public void step(Actor a) {
		int dir = rand.nextInt(4);
		if (Math.random() < threshold) {
			return;
		} else {
			switch (dir) {
			case 0: // move down
				if (a.getRow() < maxRow && a.getRow() >= minRow) {
					a.setRow(a.getRow() + 1);
				} else if (a.getRow() == maxRow) {
					a.setRow(minRow);
				}
				break;
			case 1: // move up
				if (a.getRow() <= maxRow && a.getRow() > minRow) {
					a.setRow(a.getRow() - 1);
				} else if (a.getRow() == minRow) {
					a.setRow(maxRow);
				}
				break;
			case 2: // move left
				if (a.getCol() <= maxCol && a.getCol() > minCol) {
					a.setCol(a.getCol() - 1);
				} else if (a.getCol() == minCol) {
					a.setCol(maxCol);
				}
				break;
			case 3: // move right
				if (a.getCol() < maxCol && a.getCol() >= minCol) {
					a.setCol(a.getCol() + 1);
				} else if (a.getCol() == maxCol) {
					a.setCol(minCol);
				}
				break;
			}
		}
	}

	private Actor gerRandomActor(Actor.State state, int maxX, int maxY, double beta, double gamma) {
		Random r = new Random();
		return new Actor(state, r.nextInt(maxX), r.nextInt(maxY), beta, gamma);
	}

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public List<Actor> getActors() {
		return actors;
	}

	public boolean isFinished() {
		for (Actor a : actors) {
			if (a.getState() == State.SICK) {
				return false;
			}
		}
		return true;
	}

	public int numberOfSick() {
		int sum = 0;
		for (Actor a : actors) {
			if (a.getState() == State.SICK) {
				sum++;
			}
		}
		return sum;
	}

	public int numberOfHealthy() {
		int sum = 0;
		for (Actor a : actors) {
			if (a.getState() == State.HEALTHY) {
				sum++;
			}
		}
		return sum;
	}

	public int numberOfCured() {
		int sum = 0;
		for (Actor a : actors) {
			if (a.getState() == State.IMMUNE) {
				sum++;
			}
		}
		return sum;
	}

}
