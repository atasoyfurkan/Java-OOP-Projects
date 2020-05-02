import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws FileNotFoundException {
		File inputFile =  new File(args[0]);
		File outputFile = new File(args[1]);
		PrintStream output = new PrintStream(outputFile);
		Scanner input = new Scanner(inputFile);
		String s = input.next();
		String[] stringTable = s.split("-");
		int n = (int) Math.sqrt(stringTable.length);
		int[][] initialTable = new int[n][n];
		int counter = 0, x = 0, y = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				initialTable[i][j] = Integer.parseInt(stringTable[counter++]);
				if(initialTable[i][j] == 0) {
					x = j;
					y = i;
				}
			}
		}
		Tree tree = new Tree(initialTable, x, y);
		if(!tree.process(output)) output.println("N");
	}
}

class Tree {
	public static String CORRECT_TABLE;
	Node root;
	Set<String> nodes;
	
	public Tree(int[][] table, int x, int y) {
		root = new Node(table, "", x, y);
		nodes = new HashSet<String>();
		int[][] correctTable = new int[table.length][table.length];
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table.length; j++) {
				correctTable[i][j] = i * table.length + j + 1;
				if(i == table.length - 1 && j == table.length - 1)
					correctTable[i][j] = 0;
			}
		}
		CORRECT_TABLE = Arrays.deepToString(correctTable);
	}
	
	boolean process(PrintStream output) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		nodes.add(Arrays.deepToString(root.data));
		while(!q.isEmpty()) {
			Node current = q.remove();
			if(Arrays.deepToString(current.data).equals(CORRECT_TABLE)) {
				output.print(current.path);
				return true;
			}
			current = move(current);
			if(current.left != null) q.add(current.left);
			if(current.right != null) q.add(current.right);
			if(current.up != null) q.add(current.up);
			if(current.down != null) q.add(current.down);
		}
		return false;
	}
	private Node move(Node current) {
		
		if(current.x != 0) { // left move
			add(current, "L", current.x - 1, current.y);
		}
		if(current.x != current.data.length - 1) { // right move
			add(current, "R", current.x + 1, current.y);
		}
		if(current.y != 0) { // up move
			add(current, "U", current.x, current.y - 1);
		}
		if(current.y != current.data.length - 1) { // down move
			add(current, "D", current.x, current.y + 1);
		}
		return current;
	}
	
	private void add(Node current, String move, int newX, int newY) {
		int[][] table = new int[current.data.length][current.data.length];
		for(int i = 0; i < current.data.length; i++) 
			table[i] = Arrays.copyOf(current.data[i], current.data[i].length);
		int temp = table[current.y][current.x];
		table[current.y][current.x] = table[newY][newX];
		table[newY][newX] = temp;
		Node child = new Node(table, current.path + move, newX, newY);
		String s = Arrays.deepToString(child.data);
		if(!nodes.contains(s) || current == root) {
			nodes.add(s);
			if(move.equals("L")) current.left = child;
			if(move.equals("R")) current.right = child;
			if(move.equals("U")) current.up = child;
			if(move.equals("D")) current.down = child;
		}
	}
	
	private class Node {
		int[][] data;
		Node left, right, up, down;
		String path;
		int x, y;
		
		public Node(int[][] table, String path, int x, int y) {
			left = right = up = down = null;
			this.path = path;
			this.x = x;
			this.y = y;
			data = table;
		}
	}
}