
package sugarol;

import java.util.Scanner;


public class Sugarol {

    
  
       
        
    public static void main(String[] args) {
     Scanner sc = new  Scanner(System.in);
     
     boolean  exit = true;
     do{
         System.out.println("\n---------------------------");
        System.out.println("Welcome to the Animal clinic system:");
        System.out.println("");
        System.out.println("1.animal:");
        System.out.println("2.Owner:");
         System.out.println("3.Report:");
        System.out.println("4.Exit:");
        
        
        System.out.println("Enter Choice:");
        int act = sc.nextInt();
        
        switch(act){
            case 1:
                animal al = new animal();
                al.atransaction();
                break;  
            case 2:
                owner or =new owner();
                or.otransaction();
                break;
                
            case 3:
                System.out.print("Exit Selected....type'yes' to continue:");
                String resp = sc.next();
                if(resp.equalsIgnoreCase("yes")){
                exit =false;
                }
                break;
        }  
     }while(exit);
     }
     

     }
        
    


