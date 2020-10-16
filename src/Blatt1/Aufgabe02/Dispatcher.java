package Blatt1.Aufgabe02;

import java.util.Arrays;

public class Dispatcher {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(execute(new SquareCalculation(), 20)));
	}
	
	public static int[] execute(F f, int n) {
		Result result = new Result(n);
		
		for(int i = 0; i < n; i++) {
			DispatcherExampleThread thread = new DispatcherExampleThread(result, i, f);
			thread.start();
		}
		
		return result.getEndResult();
	}
}
