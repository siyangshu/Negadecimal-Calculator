import java.util.*;

/**
* A simple negadecimal calculator
*
* Author : Siyang Shu
*          Alex Fiuk
*/

public class NegadecimalCalculator {
	private int previousResult = 0;
	
	public static void main(String[] args) {
		NegadecimalCalculator calc = new NegadecimalCalculator();
		calc.REPL();
	}
	
	public void REPL() {
		// it's a loop:
		// read command from console, evaluate it, and print the output
		Scanner scanner = new Scanner(System.in);
		String instruction;
		while(true){
			System.out.print("Compute: ");
			//read
			instruction = scanner.nextLine();
			if(!legal(instruction)){
				System.out.println("Error");
				continue;
			}
			if(instruction.equals("quit")){
				System.out.println("Done");
				break;
			}
			//evaluate
			//print
			System.out.println(evaluate(instruction));
			//loop
		}
		scanner.close();
	}
	
	private boolean legal(String instruction){
		// is the instruction legal ?
		String s = instruction.replaceAll("\\s", "");
		s.toLowerCase();
		char ch[] = {'+','-','*','/','%'};
		if(s.equals("")) return false;
		if("quit".equals(s) || "clear".equals(s)) return true;
		else if(s == "") return false;
		else if(s.startsWith("decimal")) {
			if(s.substring(7).startsWith("-")) {
				return (s.substring(8).matches("[0-9]+"));
			}
			else return (s.substring(7).matches("[0-9]+"));
		}
		else if(s.matches("[0-9]+")) return true; //input = ndn
		else if(String.valueOf(ch).contains(s.substring(0, 1))) {
			return s.substring(1).matches("[0-9]+");	
		}
		else if(s.startsWith("~") || s.startsWith("?")) {
			return s.substring(1).matches("");
		}
		else if(s == "") return false;
		else return false;
	}
	
	public String evaluate(String instruction) {
		// give an instruction, evaluate and return the result as a string
		ArrayList<String> opeList = getSubInstruction(instruction);
		// break instruction to two parts: operator and operand
		String operator = opeList.get(0);    // operator, like + -
		String operand = opeList.get(1);     // operand, like ndn
		NegadecimalNumber result = new NegadecimalNumber(previousResult);
		NegadecimalNumber ndn;
		if(operand.equals("")){
			ndn = new NegadecimalNumber("0");
		}else if(operator.equals("decimal")){
			ndn = new NegadecimalNumber(Integer.parseInt(operand));
		}else{
			ndn = new NegadecimalNumber(operand);			
		}
		if(operator.equals("")){
			result = new NegadecimalNumber(operand);
		}else if(operator.equals("+")){
			result = result.add(ndn);
		}else if(operator.equals("-")){
			result = result.subtract(ndn);
		}else if(operator.equals("*")){
			result = result.multiply(ndn);
		}else if(operator.equals("/")){
			if(ndn.decimalValue() == 0){
				return "Error";
			}
			result = result.divide(ndn);
		}else if(operator.equals("%")){
			result = result.remainder(ndn);
		}else if(operator.equals("~")){
			result = result.negate();
		}else if(operator.equals("?")){
			return result + " (decimal " + previousResult + ")";
		}else if(operator.equals("decimal")){
			result = new NegadecimalNumber(Integer.parseInt(operand));
		}else if(operator.equals("clear")){
			result = new NegadecimalNumber(0);
		}else{
			return "I can't understand it!";
		}
		previousResult = result.decimalValue();
		return result.toString();
	}

	private ArrayList<String> getSubInstruction(String instruction){
		// given an instruction, resolve it to operator and operand
		// return : li[0] = operator, li[1] = operand
		if(!legal(instruction)){
			System.out.println("Error, instruction illegal");
			return null;
		}
		ArrayList<String> li = new ArrayList<String>();
		String s = normalize(instruction);
		if(isAllNumber(s)){
			// instruction is :
			// ndn
			li.add("");
			li.add(s);
		}else if(isArithmaticWithNumber(s)){
			// instruction is :
			// + - * / % ndn
			li.add(s.substring(0, 1));
			li.add(s.substring(1, s.length()));
		}else if(isSingleOperator(s)){
			// instruction is :
			// ~ ?
			li.add(s);
			li.add("");
		}else{
			// instruction is :
			// decimal integer   or   clear
			int index = 0;
			if(s.matches(".*[-0-9].*")){
				// decimal integer
				while(s.substring(index, index + 1).matches("[^-0-9]")){
					index++;
				}
				li.add(s.substring(0, index));
				li.add(s.substring(index, s.length()));
			}else{
				//clear 
				li.add(s);
				li.add("");
			}
		}
		return li;
	}
	
	private boolean isArithmaticWithNumber(String instruction){
		//  + - * / % ndn
		char ch = instruction.charAt(0);
		return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
	}
	
	private boolean isSingleOperator(String instruction){
		//  ~ ?
		return instruction.equals("~") || instruction.equals("?");
	}

	private boolean isAllNumber(String instruction){
		// ndn 
		return instruction.matches("[0-9]+");
	}
	
	
	private String normalize(String instruction){
		String s = instruction;
		s = s.replaceAll("\\s","");
		s.toLowerCase();
		return s;
	}

}
