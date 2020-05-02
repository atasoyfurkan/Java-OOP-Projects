package Main;
import java.util.*;
import CargoTrain.*;
import Util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File inputF = new File(args[0]);
		File outputF = new File(args[1]);
		readAndInitialize(inputF, outputF);
		
	}
	public static void readAndInitialize(File inputF, File outputF) throws FileNotFoundException {
		Scanner input = new Scanner(inputF);
		int length = input.nextInt();
		int carCapacity = input.nextInt();
		int stationNumber = input.nextInt();
		ArrayList<Station> stations = new ArrayList<Station>();
		Train train = new Train(length, carCapacity);
		for(int i = 0; i < stationNumber; i++) {
			stations.add(new Station(i));
		}
		while(input.hasNextInt()) {
			Cargo cargo = new Cargo(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
			stations.get(cargo.getLoadingStation()).getCargoQueue().add(cargo);
		}
		execute(train, stations, outputF);
		input.close();
	}
	public static void execute(Train train, ArrayList<Station> stations, File outputF) throws FileNotFoundException {
		PrintStream printStream = new PrintStream(outputF);
		Station.printStream = printStream;
		for(int i = 0; i < stations.size(); i++) {
			stations.get(i).process(train);
		}
	}
}