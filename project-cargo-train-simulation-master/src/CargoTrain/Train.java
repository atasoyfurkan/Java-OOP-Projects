package CargoTrain;
import java.util.*;
import Util.*;

public class Train {
	private int carCapacity;
	private int length;
	private Carriage head;
	private Carriage tail;
	
	public Train(int length, int carCapacity) {
		this.length = length;
		this.carCapacity = carCapacity;
		Carriage current = null;
		if(length > 0) {
			head = new Carriage(carCapacity);
			current = head;
		}
		for(int i = 0; i < length - 1; i++) {
			current.next = new Carriage(carCapacity);
			current.next.prev = current;
			current = current.next;
		}
		tail = current;
	}
	
	public void load(Queue<Cargo> cargos) {
		if(!cargos.isEmpty()) {
			head = new Carriage(carCapacity);
			length = 1;
			tail = head;
		}
		else {
			length = 0;
			head = tail = null;
		}
		while(!cargos.isEmpty()) {
			Cargo cargo = cargos.remove();
			Carriage current = head;
			boolean flag = false;
			while(current != null && !flag) {
				if(current.getEmptySlot() >= cargo.getSize()) {
					current.push(cargo);
					flag = true;
				}
				current = current.next;
			}
			if(!flag) {
				tail.next = new Carriage(carCapacity);
				tail.next.prev = tail;
				length++;
				tail = tail.next;
				tail.push(cargo);
			}
		}	
	}
	public void unload(Queue<Cargo> cargos) {
		Carriage current = head;
		while(current != null) {
			Stack<Cargo> carriageCargos = current.getCargos();
			while(!carriageCargos.isEmpty()) {
				cargos.add(carriageCargos.pop());
			}
			current.setEmptySlot(carCapacity);
			current = current.next;
		}
	}
	public int getLength() {
		return length;
	}
}