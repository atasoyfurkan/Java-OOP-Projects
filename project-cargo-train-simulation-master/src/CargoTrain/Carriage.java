package CargoTrain;
import java.util.*;
import Util.*;

public class Carriage {
	private int emptySlot;
	private Stack<Cargo> cargos;
	Carriage next;
	Carriage prev;
	
	Carriage(int capacity) {
		emptySlot = capacity;
		cargos = new Stack<Cargo>();
		next = prev = null;
	}
	public boolean isFull() {
		return emptySlot == 0;
	}
	public void push(Cargo cargo) {
		cargos.push(cargo);
		emptySlot -= cargo.getSize();
	}
	public Cargo pop() {
		emptySlot += cargos.peek().getSize();
		return cargos.pop();
	}
	public int getEmptySlot() {
		return emptySlot;
	}
	public void setEmptySlot(int emptySlot) {
		this.emptySlot = emptySlot;
	}
	public Stack<Cargo> getCargos() {
		return cargos;
	}
}