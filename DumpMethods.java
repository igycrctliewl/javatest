import java.lang.reflect.*;

public class DumpMethods {
	public static void main(String args[]) {
		try {
			Class c = Class.forName(args[0]);
			Method m[] = c.getDeclaredMethods();
			for (int i = 0; i < m.length; i++)
			System.out.println(m[i].toString());
		}
		catch (Throwable e) {
			System.err.println(e);
		}

		DumpMethods d = new DumpMethods();
		d.someMethod();

	}


	private void someMethod() {
		System.out.println( "in someMethod" );
		System.out.println( "from Thread:" + Thread.currentThread().getStackTrace() );

		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[1]; //coz 0th will be getStackTrace so 1st
		String methodName = e.getMethodName();
		System.out.println(methodName);
	}
}