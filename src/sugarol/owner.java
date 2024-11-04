
package sugarol;

import java.util.Scanner;
import java.util.regex.Pattern;


public class owner {
    
     Scanner sc = new Scanner(System.in);

    public void otransaction() {
        String response;
        do {
            System.out.println("\n---------------------------");
            System.out.println("Welcome to the owner of the Animal:");
            System.out.println("1. Add Owner:");
            System.out.println("2. View Owner:");
            System.out.println("3. Update Owner:");
            System.out.println("4. Delete Owner:");
            System.out.println("5. Exit:");

            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            owner or = new owner(); 
            switch (act) {
                case 1:
                    or.addowner();
                   
                    break; 
                case 2:
                    or.viewowner();
                                            
                    break;
                case 3: 
                      or.viewowner();
                        or.updateowner();
                          or.viewowner();
                    break;
                case 4:  or.viewowner();
                         or.deleteowner();
                          or.viewowner();
                    
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
    
  public void addowner() {
    Scanner sc = new Scanner(System.in); 
    String ownerName;
    String ownerNumber;
    String ownerEmail;
    String ownerAddress;

    
    while (true) {
        System.out.print("Enter Owner Name: ");
        ownerName = sc.nextLine().trim();
        if (!ownerName.isEmpty()) {
            break; 
        } else {
            System.out.println("Owner name cannot be empty. Please enter a valid name.");
        }
    }

   
    while (true) {
        System.out.print("Enter Owner Phone Number: ");
        ownerNumber = sc.nextLine().trim();
        if (isValidPhoneNumber(ownerNumber)) {
            break; 
        } else {
            System.out.println("Invalid phone number. Please enter a valid number.");
        }
    }

    
    while (true) {
        System.out.print("Enter Owner Email: ");
        ownerEmail = sc.nextLine().trim();
        if (isValidEmail(ownerEmail)) {
            break; // Valid email
        } else {
            System.out.println("Invalid email address. Please enter a valid email.");
        }
    }

    
    while (true) {
        System.out.print("Enter Owner Address: ");
        ownerAddress = sc.nextLine().trim();
        if (!ownerAddress.isEmpty()) {
            break; // Valid address
        } else {
            System.out.println("Owner address cannot be empty. Please enter a valid address.");
        }
    }

  
    String qry = "INSERT INTO tbl_owner (o_name, o_number, o_email, o_address) VALUES (?, ?, ?, ?)";
    config conf = new config(); // Assuming 'Config' class starts with a capital letter

    // Call to addRecord with validated inputs
    conf.addRecord(qry, ownerName, ownerNumber, ownerEmail, ownerAddress);
    System.out.println("Owner added successfully.");
}


private boolean isValidPhoneNumber(String number) {
    // Simple regex for phone number validation (adjust as needed)
    return number.matches("\\d{11}"); 
}

// Example validation method for email
private boolean isValidEmail(String email) {
    // Simple regex for email validation (adjust as needed)
    return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
}
private boolean isValidDate(String date) {
    String regex = "^\\d{4}-\\d{2}-\\d{2}$";
    return Pattern.matches(regex, date);
}
public void viewowner() {
    String qry = "SELECT * FROM tbl_owner";
    String[] headers = {"o_id", "o_name", "o_number", "o_email", "o_address"}; 
    String[] columns = {"o_id", "o_name", "o_number", "o_email", "o_address"}; 

    config conf = new config();
    conf.viewRecords(qry, headers, columns); 
}


    private void updateowner() {
    config conf = new config(); 

    System.out.print("Enter ID to update: ");
    int ownerId = sc.nextInt();
    sc.nextLine();

    while (conf.getSingleValue("SELECT o_id FROM tbl_owner WHERE o_id = ?", ownerId) == 0) {
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Owner ID Again: ");
        ownerId = sc.nextInt();
        sc.nextLine(); 
    }

    System.out.print("Enter New Owner Name: ");
    String newOwnerName = sc.nextLine();

    System.out.print("Enter New Owner Phone Number: ");
    String newOwnerNumber = sc.nextLine();

    System.out.print("Enter New Owner Email: ");
    String newOwnerEmail = sc.nextLine();

    System.out.print("Enter New Owner Address: ");
    String newOwnerAddress = sc.nextLine();

    String qry = "UPDATE tbl_owner SET o_name = ?, o_number = ?, o_email = ?, o_address = ? WHERE o_id = ?";
    conf.updateRecord(qry, newOwnerName, newOwnerNumber, newOwnerEmail, newOwnerAddress, ownerId);
    System.out.println("Owner updated successfully.");
}

public void deleteowner() {
    config conf = new config(); 
    System.out.print("Enter ID to Delete: ");
    int ownerId = sc.nextInt();
    sc.nextLine(); 

    while (conf.getSingleValue("SELECT o_id FROM tbl_owner WHERE o_id = ?", ownerId) == 0) {
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Owner ID Again: ");
        ownerId = sc.nextInt();
        sc.nextLine(); 
    }

    String qry = "DELETE FROM tbl_owner WHERE o_id = ?";
    conf.deleteRecord(qry, ownerId);
    System.out.println("Owner deleted successfully.");
} 
}