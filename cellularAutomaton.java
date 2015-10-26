import java.util.*;
public class cellularAutomaton
{
	public static void main(String[] args)
	{
		//some initializations
		boolean[] code=new boolean[8];
		int temp=0;
		
		//number of lines to print
		Scanner input=new Scanner(System.in);
		System.out.print("Please enter the number of lines to be displayed: ");
		int n=input.nextInt();
		System.out.println("");

		//getting the rule to be used
		System.out.print("Please enter the rule to be used (enter 256 to set each rule individually): ");
		temp=input.nextInt();

		System.out.println("\f");
			//custom rule
		if (temp==256)
		{
			for (int i=0; i<8; i++)
			{	
				temp=input.nextInt();
				code[i]= temp==1 ? true:false;
			}
		}
			//using Wolfram's convention for rule name
		else
		{	
			int[] rule=toBinary(temp);

			for (int i=0; i<8; i++)
			{	
				code[i]= rule[i]==1 ? true:false;
			}
		}
		
		//initializing the cells
		boolean[][] graph=new boolean[n][2*n+1];
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<2*n+1; j++)
			{
				graph[i][j]=false;
			}
		}

		//setting the generator
		graph[1][n]=true;

		//calculating the status of each cell
		for (int i=2; i<n-1; i++)
		{
			for (int j=1; j<2*n; j++)
			{
				graph[i][j]=checkpattern(graph, code, i, j);
			}
		}

		//printing the cells
		for (int i=1; i<n-1; i++)
		{
			for (int j=1; j<2*n; j++)
			{
				if (graph[i][j])
				{
					System.out.print("*");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
	
	public static boolean checkpattern(boolean graph[][], boolean code[], int i, int j)
	{
		//setting the behavior
		if (graph[i-1][j-1] && graph[i-1][j] && graph[i-1][j+1])
		{
			return code[0];
		}

		else if (graph[i-1][j-1] && graph[i-1][j] && !graph[i-1][j+1])
		{
			return code[1];
		}

		else if (graph[i-1][j-1] && !graph[i-1][j] && graph[i-1][j+1])
		{
			return code[2];
		}

		else if(graph[i-1][j-1] && !graph[i-1][j] && !graph[i-1][j+1])
		{
			return code[3];
		}
		else if (!graph[i-1][j-1] && graph[i-1][j] && graph[i-1][j+1])
		{
			return code[4];
		}

		else if(!graph[i-1][j-1] && graph[i-1][j] && !graph[i-1][j+1])
		{
			return code[5];
		}

		else if(!graph[i-1][j-1] && !graph[i-1][j] && graph[i-1][j+1])
		{
			return code[6];
		}

		else if(!graph[i-1][j-1] && !graph[i-1][j] && !graph[i-1][j+1])
		{
			return code[7];
		}
		else
			return false;
	}
	public static int[] toBinary(int n)
	{
		int[] bin=new int[8];
		//start at 7
		bin[7]=n%2;
		bin[6]=n/2;
		int counter=6;
		while (bin[counter]>1)
		{
			counter--;
			bin[counter]=bin[counter+1]/2;
			bin[counter+1]%=2;
		}
		return bin;
	}	
}