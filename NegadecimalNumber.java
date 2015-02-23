import java.util.*;

public class NegadecimalNumber {

	private int decNum;

	public NegadecimalNumber(String s){
		//This constructor expects a string representation of a negadecimal number, which it converts to a (decimal) int and stores in an instance variable. However, if the string does not consist entirely of digits, or is an empty string, an IllegalArgumentException should be thrown.
		if(!s.matches("[0-9]+")){
			throw new IllegalArgumentException();
		}
		decNum = convertToDecimal(Integer.parseInt(s));
	}
	public NegadecimalNumber(int n){
		//Saves the integer (decimal) parameter in an instance variable.
		decNum = n;
	}

	public NegadecimalNumber add(NegadecimalNumber ndn){
		//Returns a new negadecimal number which is the result of adding ndn to "this" negadecimal number.
		return new NegadecimalNumber(decNum + ndn.decimalValue());
	}
	public NegadecimalNumber subtract(NegadecimalNumber ndn){
		//Returns a new negadecimal number which is the result of subtracting ndn from "this" negadecimal number.
		return new NegadecimalNumber(decNum - ndn.decimalValue());
	}
	public NegadecimalNumber multiply(NegadecimalNumber ndn){
		//Returns a new negadecimal number which is the result of multiplying ndn and "this" negadecimal number.
		return new NegadecimalNumber(decNum * ndn.decimalValue());
	}
	public NegadecimalNumber divide(NegadecimalNumber ndn){
		//Returns a new negadecimal number which is the result of dividing "this" negadecimal number by ndn. Throws an ArithmeticException if ndn is zero.
		if(ndn.decimalValue() == 0){
			throw new ArithmeticException();
		}
		return new NegadecimalNumber(decNum / ndn.decimalValue());
	}
	public NegadecimalNumber remainder(NegadecimalNumber ndn){
		//Returns a new negadecimal number which is the remainder after dividing "this" negadecimal number by ndn. Throws an ArithmeticException if ndn is zero.
		if(ndn.decimalValue() == 0){
			throw new ArithmeticException();
		}
		return new NegadecimalNumber(decNum % ndn.decimalValue());
	}
	public NegadecimalNumber negate(){
		//Returns a new negadecimal number which which, when added to negadecimal, would give zero.
		return new NegadecimalNumber(-decNum);
	}
	public int decimalValue(){
		//Returns the decimal equivalent of this negadecimal number.
		return decNum;
	}
	public boolean equals(NegadecimalNumber ndn){
		//Returns true if this number and ndn have the same value. Necessary for unit testing.
		return decNum == ndn.decimalValue();
	}
	public String toString(){
		//Returns the string representation of this negadecimal number.
		return convertToNegadecimal(decNum) + "";
	}
	
	private int convertToDecimal(int negadecimal){
		// convert a negadecimal number to decimal number
		// the n position weighs (-10)^n in negadecimal instead of (10)^n in decimal
		int decimal = 0;
		int length = calcNumberLength(negadecimal);
		for(int i = 0; i < length; i++){
			int negaDigit = (negadecimal / (int)Math.pow(10, i)) % 10;
			decimal += (int)Math.pow(-10, i) * negaDigit;
		}
		return decimal;
	}

	private int convertToNegadecimal(int decimal){
		// convert a decimal number to negadecimal number
		ArrayList<Integer> li = convertToDigitsList(decimal);
		// break decimal number to list of digits
		int i = 0;
		while(true){	
			// adjust every digits to proper range, that is, when it is in odd position, range from 0-9, even position, range from (-9)-0
			if(li.size()-1 == i){
				li.add(0);
			}
			int digit = li.get(i);
			if(calcNumberLength(digit) > 1){
				// if i has more than 1 digit, carry
				li.set(i + 1, li.get(i + 1) + digit / 10);
				digit = digit - (digit / 10) * 10;
			}
			if(digit > 0 && i % 2 == 1){
				// digit > 0, but it should < 0
				li.set(i, digit - 10);
				li.set(i + 1, li.get(i + 1) + 1);     // adjust the more significant digit to maintain the value of the number
			}else if(digit < 0 && i % 2 == 0){
				// digit < 0, but it should > 0
				li.set(i, digit + 10);
				li.set(i + 1, li.get(i + 1) - 1);	     // adjust the more significant digit to maintain the value of the number			
			}else{
				li.set(i, digit);
			}
			i++;
			if(i == li.size()-1 && li.get(i) == 0){
				break;
			}
		}
		int num = 0;
		for(i = li.size() - 1; i >= 0; i--){
			// join list to negadecimal number
			num *= 10;
			num += Math.abs(li.get(i));
		}
		return num;
	}
	
	private ArrayList<Integer> convertToDigitsList(int n){
		// break the integer to list of digits
		// li.get(0): least significant number.
		// li.get(li.size()-1): most significant number.
		int num = n;
		ArrayList<Integer> li = new ArrayList<Integer>();
		if(num == 0){
			li.add(0);
			return li;
		}
		boolean positive;
		if(num > 0){
			positive = true;
		}else{
			positive = false;
		}
		num = Math.abs(num);
		int length = calcNumberLength(num);
		for(int i = 0; i < length; i++){
			if(positive){
				li.add(num % 10);
			}else{
				li.add(-(num % 10));
			}
			num = num / 10;
		}
		return li;
	}

	private int calcNumberLength(int num){
		num = Math.abs(num);
		return String.valueOf(num).length();
	}
}
