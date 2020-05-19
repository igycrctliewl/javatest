import java.math.BigInteger;
import java.math.MathContext;

public class Puzzler2 {

	public static void main( String[] args ) {
		BigInteger number = new BigInteger( "334912740121562" );
		System.out.println( Puzzler2.sqrtN( number ) );
	}


	public static BigInteger sqrtN(BigInteger in) {
		final BigInteger TWO = BigInteger.valueOf(2);
		int c;

		// Significantly speed-up algorithm by proper select of initial approximation
		// As square root has 2 times less digits as original value
		// we can start with 2^(length of N1 / 2)
		BigInteger n0 = TWO.pow(in.bitLength() / 2);
		// Value of approximate value on previous step
		BigInteger np = in;

		do {
			// next approximation step: n0 = (n0 + in/n0) / 2
			n0 = n0.add(in.divide(n0)).divide(TWO);

			// compare current approximation with previous step
			c = np.compareTo(n0);

			// save value as previous approximation
			np = n0;

			// finish when previous step is equal to current
			}  while (c != 0);

		return n0;
	}
}