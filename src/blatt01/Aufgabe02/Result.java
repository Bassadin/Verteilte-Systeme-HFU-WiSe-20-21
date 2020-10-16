package blatt01.Aufgabe02;

public class Result {
	int amountOfValues;
	int remainingCalls;

	public Result(int amountOfValues) {
		this.amountOfValues = amountOfValues;
		resultData = new int[amountOfValues];
		remainingCalls = amountOfValues;
	}

	private int[] resultData;

	public synchronized int[] getEndResult() {
		if (remainingCalls > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return resultData;
	}

	public synchronized void setSingleResult(int resultIndex, int resultValue) {
		resultData[resultIndex] = resultValue;
		remainingCalls--;
		if (remainingCalls == 0) {
			notify();
		}
	}

}
