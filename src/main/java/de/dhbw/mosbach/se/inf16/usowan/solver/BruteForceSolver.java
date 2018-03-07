package de.dhbw.mosbach.se.inf16.usowan.solver;

import de.dhbw.mosbach.se.inf16.usowan.game.Board;
import de.dhbw.mosbach.se.inf16.usowan.game.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BruteForceSolver
{
	private Board board;
	private List<Field> toggleable;

	public BruteForceSolver(Board board) {
		this.board = board;
		this.toggleable = Stream.of(board.getFields())
				.filter(Field::isToggleField)
				.collect(Collectors.toList());

		System.out.println("Brute Forcing 2^" + this.toggleable.size());
	}

	public boolean solve() {
		return solveRecurse(0);
	}

	private boolean solveRecurse(int i) {
		if(i == toggleable.size())
			return false;
		if(board.isValid()) {
			return true;
		}
		if(solveRecurse(i+1))
			return true;

		toggleable.get(i).toggle();
		if(board.isValid())
			return true;

		if(solveRecurse(i + 1)) {
			return true;
		}

		toggleable.get(i).toggle();
		return false;
	}
}
