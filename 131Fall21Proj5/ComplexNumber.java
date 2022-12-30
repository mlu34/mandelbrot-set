
//this class takes in two MyDouble values to store complex numbers; 
//the first MyDouble value is the real value;
//the second MyDouble value is an imaginary value
public class ComplexNumber {

	private final MyDouble real;
	private final MyDouble imag;   

	//this method is a constructor that takes two MyDouble parameters and
	//assigns those values to their real and imag variables
	public ComplexNumber(MyDouble real, MyDouble imag) {
		this.real = real;
		this.imag = imag;
	}

	//this method is a constructor that takes a MyDouble parameter and sets
	//it as the real variable; it assigns the imag value to 0
	public ComplexNumber(MyDouble real) {
		this(real, new MyDouble(0));
	}

	//this method is a copy constructor and sets the value of real and imag
	//to the parameter's real and imag value
	public ComplexNumber(ComplexNumber other) {
		this(other.real, other.imag);
	}

	//this method is a getter and returns the value of the current object's
	//real value
	public MyDouble getReal() {
		return this.real;
	}

	//this method is a getter and returns the value of the current object's 
	//imag value
	public MyDouble getImag() {
		return this.imag;
	}

	//this method adds the current object and the parameter together; it 
	//returns a new ComplexNumber that equals the sum of their real and imag 
	//values
	public ComplexNumber add(ComplexNumber other) {

		final MyDouble otherReal, otherImag;
		MyDouble sumOfReal, sumOfImag;

		otherReal = other.getReal();
		otherImag = other.getImag();

		sumOfReal = real.add(otherReal);
		sumOfImag = imag.add(otherImag);

		return new ComplexNumber(sumOfReal, sumOfImag);
	}

	//this method subtracts the parameter(ComplexNumber) from the current object
	//it returns a new ComplexNumber that equals the difference of their real
	//and imag values
	public ComplexNumber subtract(ComplexNumber other) {

		final MyDouble otherReal, otherImag;
		MyDouble diffOfReal, diffOfImag;

		otherReal = other.getReal();
		otherImag = other.getImag();

		diffOfReal = real.subtract(otherReal);
		diffOfImag = imag.subtract(otherImag);

		return new ComplexNumber(diffOfReal, diffOfImag);
	}

	//this method multiplies two ComplexNumbers together;
	//it returns a new ComplexNumber that equals the product of the current 
	//object and parameter
	public ComplexNumber multiply(ComplexNumber other) {

		final MyDouble otherReal, otherImag;
		MyDouble realProduct, imagProduct;

		otherReal = other.getReal();
		otherImag = other.getImag();

		realProduct = (real.multiply(otherReal))
				.subtract(imag.multiply(otherImag));
		imagProduct = (real.multiply(otherImag))
				.add(otherReal.multiply(imag));

		return new ComplexNumber(realProduct, imagProduct);
	}

	//this method divides two ComplexNumbers together;
	//it returns a new ComplexNumber that equals the quotient of the current 
	//object divided by the parameter
	public ComplexNumber divide(ComplexNumber other) {

		final MyDouble a, b, c, d;
		MyDouble firstNum, secondNum, den;

		a = this.getReal();
		b = this.getImag();
		c = other.getReal();
		d = other.getImag();

		firstNum = (a.multiply(c)).add(b.multiply(d));
		secondNum = (b.multiply(c)).subtract(a.multiply(d));
		den = (c.multiply(c)).add(d.multiply(d));

		return new ComplexNumber(firstNum.divide(den), secondNum.divide(den));
	}

	//this method compares two ComplexNumbers to see if the values of the two
	//objects are equals; it returns true if the current object and parameter 
	//are equal, and false if they are not equal
	public boolean equals(ComplexNumber other) {

		if(real.equals(other.getReal()) && imag.equals(other.getImag())) {
			return true;
		}
		return false;
	}

	//this method compares two ComplexNumbers
	//it returns an int value based on if the norm of the current object is less
	//than (returns -1), equal to (returns 0), or greater than (returns 1) the 
	//parameter's norm
	public int compareTo(ComplexNumber other) {

		final MyDouble otherReal, otherImag;
		MyDouble currNorm, otherNorm;
		int returnVal = 0;

		otherReal = other.getReal();
		otherImag = other.getImag();

		currNorm = real.multiply(real)
				.add(imag.multiply(imag)).sqrt();
		otherNorm = otherReal.multiply(otherReal)
				.add(otherImag.multiply(otherImag)).sqrt();

		if(currNorm.compareTo(otherNorm) < 0) {
			returnVal = -1;
		} else if(currNorm.compareTo(otherNorm) > 0) {
			returnVal = 1;
		}
		return returnVal;
	}

	//this method takes the current object's real and imag values and turns it
	//into a string value; it returns a String
	public String toString() {

		//this sign will be used in between the real and imag value
		String sign = "";

		if(imag.toString().indexOf('-') == -1){
			sign = "+";
		}
		return real.toString() + sign + imag.toString() + "i";
	}

	//this method takes a ComplexNumber parameter and returns a MyDouble object
	//that represents the norm of the complex number;
	//the norm of a+bi would be the sqrt(a^2 + b^2)
	public static MyDouble norm(ComplexNumber other) {
		
		final MyDouble otherReal, otherImag;
		otherReal = other.getReal();
		otherImag = other.getImag();

		//this finds the norm of the ComplexNumber
		MyDouble otherNorm = otherReal.multiply(otherReal)
				.add(otherImag.multiply(otherImag)).sqrt();
		return otherNorm;
	}

	//this method takes in a String parameter and return a new ComplexNumber;
	//if the String has extra spaces, the method will remove the spaces 
	public static ComplexNumber parseComplexNumber(String input) {

		MyDouble parsedReal, parsedImag;	
		String inputNoSpace = input.replaceAll(" ", "");

		//if there is "+" in the string, then the real value is between the 
		//first character and the "+" and the imag value is between the "+" and 
		//last character; if not, then the same happens, but with "-"
		if(inputNoSpace.indexOf('+') != -1) {

			parsedReal = new MyDouble(Double.parseDouble(inputNoSpace.substring
					(0, inputNoSpace.indexOf('+'))));
			parsedImag = new MyDouble(Double.parseDouble(inputNoSpace.substring
					(inputNoSpace.indexOf('+'), inputNoSpace.indexOf('i'))));
			
		} else {
			parsedReal = new MyDouble(Double.parseDouble(inputNoSpace.substring
					(0, inputNoSpace.indexOf('-', 1))));
			parsedImag = new MyDouble(Double.parseDouble(inputNoSpace.substring
					(inputNoSpace.indexOf('-', 1), inputNoSpace.indexOf('i'))));
		}
		return new ComplexNumber(parsedReal, parsedImag);
	}


}
