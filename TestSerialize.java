import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*  to serialize and reload an object, the version ID must be the same.  If it is not specified, it
    is derived dynamically.  There is a program in the JDK, serialver, that can tell you the version id:
	 
	 mikebro@SurfacePro4 MINGW64 ~/Documents/javatest (master ? u+1)
	 $ serialver TestSerialize
	 TestSerialize:    private static final long serialVersionUID = 6472398671663857678L;

*/

public class TestSerialize implements Serializable {

	static final long serialVersionUID = 6472398671663857678L;
	
	private String instanceData;
	private int instanceNumber;
	
	public TestSerialize( String junk, int howMany ) {
		this.instanceData = junk;
		this.instanceNumber = howMany;
	}

	public String toString() {
		return this.getClass().getName() + ":" + this.instanceData + ":" + this.instanceNumber;
	}

	public static void main( String[] args ) throws Exception {
		testRead( args );
	}
	
	public static void testWrite( String[] args ) throws Exception {
		TestSerialize ts = new TestSerialize( "barney", 52 );
		System.out.println( ts );
		
		FileOutputStream fos = new FileOutputStream("test.obj");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject( ts );
		oos.close();

	}

	
	public static void testRead( String[] args ) throws Exception {
      System.out.println( ">>>> testRead" );
		
		FileInputStream fis = new FileInputStream("test.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		TestSerialize result = (TestSerialize) ois.readObject();
		ois.close();
		
		System.out.println( result );
		
	}
}