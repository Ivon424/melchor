
package sugarol;

import java.util.Scanner;
import java.util.regex.Pattern;
import jdk.nashorn.internal.runtime.regexp.joni.Config;


public class animal {
    Scanner sc = new Scanner(System.in);

    public void atransaction() {
        String response;
        do {
            System.out.println("\n---------------------------");
            System.out.println("Animal Panel:");
            System.out.println("1. Add animal:");
            System.out.println("2. View animal:");
            System.out.println("3. Update animal:");
            System.out.println("4. Delete animal:");
            System.out.println("5. Exit:");

            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            sc.nextLine(); 

            switch (act) {
                case 1:
                    addanimal();
                    break; 
                case 2:
                    viewanimal();       
                    break;
                case 3:
                    viewanimal();
                    updateanimal();
                     viewanimal();
                    break;
                case 4:
                    viewanimal();
                    deleteaanimal();
                     viewanimal();
                    
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }

            System.out.println("Do you want to continue? (yes/no):");
            response = sc.next();
            
        } while (response.equalsIgnoreCase("yes"));     
    }
   
  public void addanimal() {
    String animalName;
    String animalType;

    // Getting the animal name
    while (true) {
        System.out.print("Enter Animal Name: ");
        animalName = sc.nextLine().trim();
        if (!animalName.isEmpty()) {
            break; // Valid name
        } else {
            System.out.println("Animal name cannot be empty. Please enter a valid name.");
        }
    }

    // Getting the animal type
    while (true) {
        System.out.print("Enter Animal Type: ");
        animalType = sc.nextLine().trim();
        if (!animalType.isEmpty()) {
            break; // Valid type
        } else {
            System.out.println("Animal type cannot be empty. Please enter a valid type.");
        }
    }

    // Prepare the query
    String qry = "INSERT INTO tbl_animal (a_name, a_type) VALUES (?, ?)";
    config conf = new config() {}; // Correct instantiation of Config class

    // Call to addRecord with validated inputs
    conf.addRecord(qry, animalName, animalType); // Ensure addRecord is correctly implemented
}
private boolean isPositiveInteger(String str) {
    if (str == null || str.isEmpty()) {
        return false;
    }
    try {
        int value = Integer.parseInt(str);
        return value > 0;
    } catch (NumberFormatException e) {
        return false;
    }
}


    
    private void viewanimal() {
        String qry = "SELECT * FROM tbl_animal";
        String[] hrds = {"a_id", "a_name","a_type"};
        String[] clms = {"a_id", "a_name", "a_type"};
        config conf = new config(); 
        conf.viewRecords(qry, hrds, clms);
    }    

    
    
    private void updateanimal() {
        config conf = new config();

        System.out.print("Enter ID to update: ");
        int aid = sc.nextInt();
       

      
        while (conf.getSingleValue("SELECT a_id FROM tbl_animal WHERE a_id = ?", aid) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select animal ID Again:");
            aid = sc.nextInt();
           
        }

        System.out.print("Enter New animal Name: ");
        String aname = sc.next();
        System.out.print("Enter New Animal type: ");
        String atype = sc.next();
        ;

        while (conf.getSingleValue("SELECT a_id FROM tbl_animal WHERE a_id = ?", aid) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Animal ID Again:");
            aid = sc.nextInt();
           
        }
        String qry = "UPDATE tbl_animal SET a_name = ?, a_type = ?  WHERE a_id = ?";
        conf.updateRecord(qry, aname, atype, aid);
    }
    public void deleteaanimal(){
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter Id to Delete:");
        int aid = sc.nextInt();
         
        
          while (conf.getSingleValue("SELECT a_id FROM tbl_animal WHERE a_id = ?", aid) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Animal ID Again:");
            aid = sc.nextInt();
           
          }
        
        String qry ="DELETE FROM tbl_animal WHERE a_id =?";
   
        conf.deleteRecord(qry, aid);
    }
    }