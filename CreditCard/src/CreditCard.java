import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class CreditCard
	{
	//hi
		//branch testing
		static ArrayList<Long> creditCard = new ArrayList<Long>();
		static Scanner userInput = new Scanner(System.in);
		static long userNumber = 0;
		static int correctNums = 0;
		public static void main(String[] args)
			{
//				takeNum();
//				stripCreditCard();
//				doubleAlternates();
//				checkNumber();
//				generateCreditNums();
				readNums();

			}
		public static void takeNum()
		{
			System.out.println("Input a credit card number and I'll tell you if it's valid or not.");
			userNumber = userInput.nextLong();
		}
		public static void stripCreditCard()
		{
			boolean repeat = true;
			do
				{
				
				if(userNumber==0)
					{
//						if(creditCard.size()!=16)
//							{
//							System.out.println("That number was not 16 digits long.");
//							creditCard.clear();
//							takeNum();
//							}
//						else
//							{
						repeat =false;
//							}
					}
				else
					{
					creditCard.add(userNumber%10);
					userNumber/=10;
					}
				}
			while(repeat);
		}
		public static void doubleAlternates()
		{
			long holder=0;
			long holder2=0;
			long doubledNum=0;
			for(int i=1; i<creditCard.size();i+=2)
				{
					doubledNum=creditCard.get(i)*2;
					holder=doubledNum%10;
					holder2=doubledNum/10;
					creditCard.set(i, (holder+holder2));
				}
		
		}
		public static void checkNumber()
		{
			int sum=0;
			for(int i=0; i<creditCard.size(); i++)
				{
					sum+=creditCard.get(i);

				}
			if(sum%10==0)
				{
//					System.out.println("That is a potentially valid credit card number.");
					correctNums+=1;
					creditCard.clear();
				}
			else
				{
//					System.out.println("That is not a potentially valid credit card number.");
					creditCard.clear();
				}
		}
		public static void generateCreditNums()
		{
		String fileName = "CreditCardNums.txt";
			
		try
			{
		FileWriter fileWriter = new FileWriter(fileName, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		long startTime = System.nanoTime();
		
		ArrayList<String> creditNums = new ArrayList<String>();
		ArrayList<Long> numTester = new ArrayList<Long>();
		
		int counter=0;
		boolean repeat=true;
		
		while(repeat)
			{
				String intToStr = "";
				String cardNum="";
				long creditNum=0;
				long strippedNum=0;
				
				for(int i=0; i<16; i++)
					{
				int randomNumber= (int)(Math.random()*10);	
				intToStr = String.valueOf(randomNumber);
				cardNum+=intToStr;
					}
				creditNum=Long.parseLong(cardNum);
				strippedNum=creditNum;
				
				boolean doAgain = true;
								
				while(doAgain)
					{
					if(strippedNum==0)
						{	
							doAgain =false;
						}
					else
						{
						numTester.add(strippedNum%10);
						strippedNum/=10;
						}
					}
				
				long holder=0;
				long holder2=0;
				long doubledNum=0;
			
				for(int i=1; i<numTester.size();i+=2)
					{
						doubledNum=numTester.get(i)*2;
						holder=doubledNum%10;
						holder2=doubledNum/10;
						numTester.set(i, (holder+holder2));
					}
				
				if(numTester.size()<16)
					{
					numTester.clear();
					}
				
				int sum=0;
				
				for(int i=0; i<numTester.size(); i++)
					{
						sum+=numTester.get(i);
					}
				
				if(sum%10==0)
					{
						for(int i=0; i<creditNums.size(); i++)
							{
								if(cardNum.equals(creditNums.get(i)))
								{
								numTester.clear();
								break;
								}
					
							}
						creditNums.add(cardNum);
						numTester.clear();
					}
				else
					{
						numTester.clear();
					}
				
				if(creditNums.size()==100)
					{
						repeat=false;
					}
				counter+=1;
			}
		long endTime = System.nanoTime();
		System.out.println("It took "+counter+" attempts to generate 100 valid credit card numbers.");
		System.out.println("Took "+(endTime - startTime) + " ns."); 
			
		for(String n: creditNums)
			{
			bufferedWriter.write(n+"\n");
			}
		bufferedWriter.close();
		
			}
		
		catch(IOException ex)
			{
			ex.printStackTrace();
			}
		
		}
		public static void readNums()
		{
	
			
			try
				{
					Scanner nums = new Scanner(new File(userInput.nextLine()));
					while(nums.hasNextLine())
						{
					userNumber = Long.parseLong(nums.next());
					stripCreditCard();
					doubleAlternates();
					checkNumber();
						}
					
					System.out.println(correctNums+" of the numbers in that file are potentially valid.");
					
				
				}
			catch(FileNotFoundException ex)
				{
					ex.printStackTrace();
				}
			catch(IOException ex)
				{
					ex.printStackTrace();
				}
		}
	
		

	}
