import java.awt.Color;

public class MandelbrotTools {

	//this method takes one ComplexNumber parameter and returns a boolean;
	//it returns true if the value a^2 + b^2 (values from the ComplexNumber
	//(a + bi)) is bigger than DIVERGENCE_BOUNDARY, otherwise returns false
	public static boolean isBig(ComplexNumber other) {

		MyDouble otherReal, otherImag;
		otherReal = other.getReal();
		otherImag = other.getImag();

		MyDouble value = otherReal.multiply(otherReal)
				.add(otherImag.multiply(otherImag));

		if(value.compareTo(Controller.DIVERGENCE_BOUNDARY) > 0) {
			return true;
		}
		return false;
	}

	//this method takes a ComplexNumber parameter(z0) and returns an int;
	//it calculates a sequence of complex numbers;
	//example: z1 = z0^2 + z0, z2 = z1^2 + z0, z3 = z2^2 + z0, etc.
	public static int divergence(ComplexNumber z0) {
		ComplexNumber zValue, zTempValue;

		int index = 0;
		zValue = z0.add(z0.multiply(z0));
		zTempValue = zValue.multiply(zValue);	

		do {
			zTempValue = zValue.multiply(zValue);
			zValue = z0.add(zTempValue);
			index++;
		} while(index < 256 && isBig(zValue) == false);

		if(index > 255) {
			index = -1;
		} 
		return index;
	}

	/* This method selects a non-black color for a point which DIVERGED when 
	 * tested with the Mandelbrot recurrence, based on how many terms in the 
	 * sequence were computed before the terms got "too big".
	 * 
	 * The parameter represents the index of the term in the sequence which was 
	 * first to be "too big". This value could be anything from 0 to 
	 * Controller.LIMIT.  The return value is the Color to be used to color in 
	 * the point.
	 */
	public static Color getColor(int divergence) {
		Color returnValue;

		if (Controller.colorScheme == Controller.RED_AND_WHITE_BANDS) {
			returnValue = (divergence  % 2 == 0)? Color.WHITE : Color.RED;
		}

		else if (Controller.colorScheme == Controller.CRAZY_COLORS) {
			int value = divergence * 2;
			int redAmount = (value % 5) * (255/5);
			int greenAmount = (value % 7) * (255/7);
			int blueAmount = (value % 9) * (255/9);
			returnValue = new Color(redAmount, greenAmount, blueAmount); 

		} else if (Controller.colorScheme == Controller.STUDENT_DEFINED){
			returnValue = Color.WHITE;  

		} else
			throw new RuntimeException("Unknown color scheme selected!");
		return returnValue;
	}


}
