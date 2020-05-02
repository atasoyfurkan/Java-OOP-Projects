package Util;
import java.util.*;
import java.io.*;
import CargoTrain.*;

public class Station {
	private int id;
	private Queue<Cargo> cargoQueue;
	public static PrintStream printStream;
	
	public Station(int id) {
		this.id = id;
		cargoQueue = new LinkedList<Cargo>();
	}
	public void process(Train train) {
		train.unload(cargoQueue);
		Queue<Cargo> temp = new LinkedList<Cargo>();
		int size = cargoQueue.size();
		for(int i = 0; i < size; i++) {
			Cargo cargo = cargoQueue.remove();
			if(cargo.getTargetStation() == id) {
				printStream.println(cargo);
				temp.add(cargo);
			}
			else {
				cargoQueue.add(cargo);
			}
		}
		train.load(cargoQueue);
		cargoQueue.addAll(temp);
		printStream.println(id + " " + train.getLength());
	}
	public Queue<Cargo> getCargoQueue() {
		return cargoQueue;
	}
}