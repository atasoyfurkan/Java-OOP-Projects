package assignment;
import java.util.*;
import java.io.*;

public class assignment3 {
	/* in main method, I am reading file and filling arrays, after that it sends these info.rmations to other methods */
	public static void main(String[] args) throws FileNotFoundException {
		
		File f = new File("input.txt");
		Scanner input = new Scanner(new File("input.txt"));
		
		String temp = input.next();
		int split = temp.indexOf("x");
		int y = Integer.parseInt(temp.substring(0, split));
		int x = Integer.parseInt(temp.substring(split + 1));
		
		int array[][] =	 new int[y][x];
		String hint[][] = new String[y][x];
		
		read(hint, array, input, y, x, 0);
		yazdir(array, y, x, 0);
		System.out.println();
		test(array, hint, y, x, 0);
		yazdir(array, y, x, 0);
		
	}
	/* at first, i am making loop with recursion in test method. Secondly, i am controlling is there any F, R, C so on.
	 * After that, i am sending necessary variables to necessary method according to hint[][] array  */
	public static void test(int array[][], String hint[][], int y, int x, int counter) {
		
		int currentY = counter / x;
		int currentX = counter % x;
		
		if(counter == y * x) {
			return;
		}
		
		if(hint[currentY][currentX].equals("F")) {
			test(array, hint, y, x, counter + 1);
			return;
		}
		if(hint[currentY][currentX].equals("R")) {
			R(array, currentY, currentX, x, 0);
		}
		if(hint[currentY][currentX].equals("C")) {
			int temp[] = new int[y];
			C(array, temp, currentX, y, 0);
			Arrays.sort(temp);
			if(temp.length % 2 == 0) {
				array[currentY][currentX] = temp[temp.length / 2 - 1];
			}
			else {
				array[currentY][currentX] = temp[temp.length / 2];
			}
		}
		if(hint[currentY][currentX].equals("D")) {
			int sum[] = new int[2];
			D_situation1(array, y, x, currentY, currentX, sum);
			D_situation2(array, y, x, currentY, currentX, sum);
			D_situation3(array, y, x, currentY, currentX, sum);
			D_situation4(array, y, x, currentY, currentX, sum);
			sum[0] -= array[currentY][currentX] * 3;
			sum[1] -= 3;
			array[currentY][currentX] = sum[0] / sum[1];
		}
		if(hint[currentY][currentX].equals("N")) {
			int value = array[currentY][currentX];
			N(array, hint, y, x, currentY, currentX, value);
		}
		
		test(array, hint, y, x, counter + 1);
	}
	/* in R method, i am making recursion first to control x axis. After that, Code understands whether there is any number bigger than controlling number.
	 * If there is, taking this value in controlling array */
	public static void R(int array[][], int currentY, int currentX, int x, int counter) {
		
		if(counter == x) {
			return;
		}
		
		if(array[currentY][counter] > array[currentY][currentX]) {
			array[currentY][currentX] = array[currentY][counter];
		}
		
		R(array, currentY, currentX, x, counter + 1);
	}
	/* in C method, i am creating a array called temp[] as a column and in test method, this array is used to understand median value */
	public static void C(int array[][], int temp[], int currentX, int y, int counter) {
		
		if(counter == y) {
			return;
		}
		
		temp[counter] = array[counter][currentX];
		C(array, temp, currentX, y, counter + 1);
	}
	/* all of D_situation is making similar things. Therefo.re, i will explain them at the same time.
	 * situation1 is controlling left-back way. situation2 is controlling right-back way. situation3 is controlling left-fo.rward way. situation 4 is controlling right-fo.rward way.*/
	public static void D_situation1(int array[][], int y, int x, int counterY, int counterX, int sum[]) {
		if( counterY >= y || counterY < 0 || counterX >= x || counterX < 0 ) {
			return;
		}
		sum[0] += array[counterY][counterX];
		sum[1]++;
		D_situation1(array, y, x, counterY - 1, counterX - 1, sum);
	}
	public static void D_situation2(int array[][], int y, int x, int counterY, int counterX, int sum[]) {
		if( counterY >= y || counterY < 0 || counterX >= x || counterX < 0 ) {
			return;
		}
		sum[0] += array[counterY][counterX]; 
		sum[1]++;
		D_situation2(array, y, x, counterY - 1, counterX + 1, sum);
	}
	public static void D_situation3(int array[][], int y, int x, int counterY, int counterX, int sum[]) {
		if( counterY >= y || counterY < 0 || counterX >= x || counterX < 0 ) {
			return;
		}
		sum[0] += array[counterY][counterX]; 
		sum[1]++;
		D_situation3(array, y, x, counterY + 1, counterX - 1, sum);
	}
	public static void D_situation4(int array[][], int y, int x, int counterY, int counterX, int sum[]) {
		if( !(counterY < y && counterY >= 0 && counterX < x && counterX >= 0) ) {
			return;
		}
		sum[0] += array[counterY][counterX];
		sum[1]++;
		D_situation4(array, y, x, counterY + 1, counterX + 1, sum);
	}
	/*in N method, i am controlling recursively is there any "N" beside to control "N". If there is, method is calling method again to control same thing*/
	public static void N(int array[][], String hint[][], int y, int x, int counterY, int counterX, int value) {
		if( counterY >= y || counterY < 0 || counterX >= x || counterX < 0 ) {
			return;
		}
		
		if(counterY + 1 < y && hint[counterY + 1][counterX].equals("N")) {
			hint[counterY + 1][counterX] = "n";
			array[counterY + 1][counterX] = value;
			N(array, hint, y, x, counterY + 1, counterX, value);
		}
		if(counterY - 1 >= 0 && hint[counterY - 1][counterX].equals("N")) {
			hint[counterY - 1][counterX] = "n";
			array[counterY - 1][counterX] = value;
			N(array, hint, y, x, counterY - 1, counterX, value);
		}
		if(counterX + 1 < x && hint[counterY][counterX + 1].equals("N")) {
			hint[counterY][counterX + 1] = "n";
			array[counterY][counterX + 1] = value;
			N(array, hint, y, x, counterY, counterX + 1, value);
		}
		if(counterX - 1 >= 0 && hint[counterY][counterX - 1].equals("N")) {
			hint[counterY][counterX - 1] = "n";
			array[counterY][counterX - 1] = value;
			N(array, hint, y, x, counterY, counterX - 1, value);
		}
	}
	/* read method is reading array from file recursively. */
	public static void read(String hint[][], int array[][],Scanner input, int y, int x, int counter) {
		
		if(counter == y * x) {
			return;
		}
		
		hint[counter / x][counter % x] = input.next();
		array[counter / x][counter % x] = hint[counter / x][counter % x].charAt(1) - '0';
		hint[counter / x][counter % x] = String.valueOf(hint[counter / x][counter % x].charAt(0));
		
		read(hint, array, input, y, x, counter + 1);
	}
	/*yazdir method is printing array recursively */
	public static void yazdir(int array[][], int y, int x, int counter) {
		
		if(counter % x == 0 && counter != 0) {
			System.out.println();
		}
		
		if(counter == y * x) {
			return;
		}
		
		System.out.print(array[counter / x][counter % x] + " ");
		
		yazdir(array, y, x, counter + 1);
	}
}



