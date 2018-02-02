// interesting scheduled-task code using timer
// thanks https://stackoverflow.com/questions/9375882/how-i-can-run-my-timertask-everyday-2-pm

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	private final static long ONCE_PER_DAY = 1000 * 60 * 60 * 24;
	private final static int TWO_AM = 2;
	private final static int ZERO_MINUTES = 0;

	private Timer timer;


	public MyTimerTask() {
		super();
		this.timer = new Timer();
	}

	public Timer getTimer() {
		return this.timer;
	}


	@Override
	public void run() {
		System.out.println( "Start Job : " + System.currentTimeMillis() );
		// Do your Job Here
		System.out.println( "  End Job : " + System.currentTimeMillis() );

		// immediately terminate this Timer
		this.getTimer().cancel();
	}


	//call this method from your servlet init method
	public static void startTask() {
		MyTimerTask task = new MyTimerTask();

		// schedule the task after a ten-second delay
		task.getTimer().schedule( task, 1000 * 10 );
	}

	public static void main( String args[] ) {
		System.out.println( "MyTimerTask.main started - " + System.currentTimeMillis() );
		startTask();
		System.out.println( "MyTimerTask.main ended - " + System.currentTimeMillis() );
	}

}