import java.util.*;

public class CalcLauncher
{
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		boolean flag = true;

		do
		{
		//get user infix input
		System.out.println("Input infix expression: ");

		//create new calculator object with infix string
		String infix = keyboard.next();
		Calculator calc = new Calculator(infix); 
		
		//print infixExp
		System.out.println("Infix: " + calc.toString());

		//print postfixExp
		System.out.println("Postfix: " + calc.getPostfix());

		//calculate postfix expression and print result
		System.out.println("Result: " + calc.evaluate());
		
		//print "continue?"
		System.out.println("Continue? y/n");

		//if keyboard.next == n flag = false
		if (keyboard.next().charAt(0) == 'n') 
		{
			flag = false;	
		}
		else 
			flag = true;

		}while(flag);
		
	}
}
