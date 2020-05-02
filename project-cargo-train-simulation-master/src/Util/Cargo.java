package Util;

public class Cargo {
	private int id;
	private int loadingStation;
	private int size;
	private int targetStation;
	
	public Cargo(int id, int loadingStation, int targetStation, int size) {
		this.id = id;
		this.loadingStation = loadingStation;
		this.size = size;
		this.targetStation = targetStation;
	}
	public String toString() {
			return id + " " + loadingStation + " " + targetStation + " " + size;
 	}
	public int getSize() {
		return size;
	}
	public int getId() {
		return id;
	}
	public int getLoadingStation() {
		return loadingStation;
	}
	public int getTargetStation() {
		return targetStation;
	}
}