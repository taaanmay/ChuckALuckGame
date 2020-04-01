package chuckALuck;

import java.util.Scanner;

public class game {


	public static void main(String[] args) {
		Wallet wal = new Wallet();
		Scanner input = new Scanner(System.in);

		boolean end = false;
		System.out.println("Enter the amount of cash you have.");
		double money = input.nextDouble();

		if(money>0) {
			wal.put(money);	
		}else {
			System.out.println("Money should be greater than 0. Enter Again.");
			input.nextLine();
		}

		String typeOfBet;
		do {
			if(wal.check()==0) {
				System.out.println("Game has ended. You have "+wal.check()+". You started with "+money+".");
				break;	// if balance is 0
			}
			System.out.println("Enter the type of bet - Triple , Field , High , Low.");
			typeOfBet = input.next();
			if(typeOfBet.equalsIgnoreCase("Triple")||typeOfBet.equalsIgnoreCase("Field")||typeOfBet.equalsIgnoreCase("High")||typeOfBet.equalsIgnoreCase("Low")) {
				resolveBet(typeOfBet,wal);


			}else if(typeOfBet.equalsIgnoreCase("quit")) {
				System.out.println("Game has ended. You have "+wal.check()+". You started with "+money+".");

				end=true;
			}
			else
			{
				System.out.println("Invalid Entry. Enter the type of bet - Triple , Field , High , Low.");	
				input.nextLine();
			}
		}while(end==false);

	}

	public static void resolveBet(String typeOfBet,Wallet wal) {
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		Dice dice3 = new Dice();


		Scanner scanner = new Scanner(System.in);
		System.out.println("You have "+wal.check());
		double remainingAmount = wal.check();
		dice1.roll();
		dice2.roll();
		dice3.roll();
		boolean winner = false;
		int sumationOfTopFaces = 0;
		boolean isTriple = false; // checks if triple is true, then it does'nt trigger field, high or low.


		//Asking betting amount
		System.out.println("How much money do you want to bet.");
		double betAmount = scanner.nextDouble();

		//Checking betting amount is less than the money in wallet
		if(wal.check()>=betAmount) {

			sumationOfTopFaces =dice1.topFace()+dice2.topFace()+dice3.topFace();
			if(dice1.topFace()==dice2.topFace()&&dice2.topFace()==dice3.topFace()&&typeOfBet.equalsIgnoreCase("triple")&&dice1.topFace()!=1&&dice1.topFace()!=6) 
			{
				isTriple = true;
				winner = true;
				betAmount = 30*betAmount;
				wal.put(betAmount);
			}else if(sumationOfTopFaces<8|| sumationOfTopFaces>12 && typeOfBet.equalsIgnoreCase("field")&&isTriple==false) {
				winner = true;
				wal.put(betAmount);
			}else if(sumationOfTopFaces>10&&typeOfBet.equalsIgnoreCase("high")&&isTriple==false) {
				winner = true;
				wal.put(betAmount);
			}else if(sumationOfTopFaces<11 &&typeOfBet.equalsIgnoreCase("low")&&isTriple==false) {
				winner = true;
				wal.put(betAmount);
			}else
			{
				wal.clearWallet();
				remainingAmount=remainingAmount-betAmount;
				wal.put(remainingAmount);
			}

		}

		else 
		{
			System.out.println("You have less money in wallet. You have "+wal.check()+" Bet Accordingly. Start the game again.");
		}

		if(winner==true) {
			System.out.println("Congratulations. You have won!");
			System.out.println("Your current balance is "+wal.check()+".");
		}else {
			System.out.println("Sorry. You have lost.");
			System.out.println("Your current balance is "+wal.check()+".");
		}
	}

}




/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7:7 ].
Comment:Yes. Exactly this is done.
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8:8 ].
Comment:Yes. This is done.
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5:5 ].
Comment:Such a check is made.
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15:15 ]..
Comment:Yes. All the dices are rolled everytime. Three dice objets are created. 
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20:20 ].
Comment:Yes it checks all the cases.
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10:10 ].
Comment:Yes. Output is given and the wallet is updated.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15:15 ]
Comment:Yes, the same is done.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5:5 ]
Comment:Yes. It loops until these 2 conditions are reached.
I ask the user to enter any of the four bet types or quit [Mark out of 5:5 ].
Comment:Yes. Same is done.
My program calls resolveBet for each bet type entered [Mark out of 5:5 ].
Comment:Yes. resolveBet is called everytime.
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5:5 ]
Comment:Yes. In the end a summary message is given.

 Total Mark out of 100 (Add all the previous marks):100
 */


