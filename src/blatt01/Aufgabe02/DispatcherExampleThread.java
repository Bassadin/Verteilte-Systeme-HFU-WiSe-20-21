package blatt01.Aufgabe02;

public class DispatcherExampleThread extends Thread {
	Result threadResult;
	int threadIndex;
	F f;
	
	public void run() {
		 threadResult.setSingleResult(threadIndex, f.f(threadIndex));
	}

	public DispatcherExampleThread(Result result, int n, F f) {
		threadResult = result;
		threadIndex = n;
		this.f = f;
	}
}
