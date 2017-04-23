

/* 

Rahnuma Islam Nishat - 08/02/2014
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class  KMP 
{
   private static String pattern;
   private static int R;
   private static int[][]dfa;
   private static char[]patt;

   
   public KMP(String pattern){ 
	this.R=256;
	this.pattern=pattern;
	int M=pattern.length();
	dfa=new int[R][M];
	dfa[pattern.charAt(0)][0]=1;
	for(int X=0,j=1;j<M;j++){
		for(int c=0;c<R;c++){
			dfa[c][j]=dfa[c][X];
		}
		dfa[pattern.charAt(j)][j]=j+1;
		X=dfa[pattern.charAt(j)][X];
	}
	
	
   }
   //returns the index of the first occurence 
   public static int search(String txt){  
	int p=pattern.length();
	int t=txt.length();
	int i;
	int j;
	for(i=0,j=0;i<t && j<p;i++){
		j=dfa[txt.charAt(i)][j];
	}
	//occurance found
	if(j==p){
		return i-p;
	}
   	   return t;
   }
   private KMP(char[]patt,int R){
	   this.R=R;
	   this.patt=new char[patt.length];
	   for(int i=0;i<patt.length;i++){
		   this.patt[i]=patt[i];
	   }
	   //DFA
	   int p=patt.length;
	   dfa=new int[R][p];
	   dfa[patt[0]][0]=1;
	   for(int X=0,j=1;j<p;j++){
		   for(int c=0;c<R;c++){
			   dfa[c][j]=dfa[c][X];
		   }
		   dfa[patt[j]][j]=j+1;
		   X=dfa[patt[j]][X];
	   }
   }
   private static int search(char[]text){
	   int p=patt.length;
	   int t=text.length;
	   int i;
	   int j;
	   for(i=0,j=0;i<t && j<p;i++){
		   j=dfa[text[i]][j];
	   }
	   if(j==p){
		   return i-p;
	   }
	   return t;
   }
   

        
  	public static void main(String[] args) throws FileNotFoundException{
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.println("Unable to open "+args[0]+ ".");
				return;
			}
			System.out.println("Opened file "+args[0] + ".");
			String text = "";
			while(s.hasNext()){
				text+=s.next()+" ";
			}
			
			for(int i=1; i<args.length ;i++){
				KMP k = new KMP(args[i]);
				int index = search(text);
				if(index >= text.length())System.out.println(args[i]+ " was not found.");
				else System.out.println("The string \""+args[i]+ "\" was found at index "+index + ".");
			}
			
			//System.out.println(text);
			
		}else{
			System.out.println("usage: java SubstringSearch <filename> <pattern_1> <pattern_2> ... <pattern_n>.");
		}
		
		
	}
}




