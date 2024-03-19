import java.util.*;

public class Main {
    public static void main(String[] args){
        String nameOfUser;
        String email;
        String gender;
        String status;
        int id;
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 to Get the AllUsers :");
            System.out.println("2 to Get the User By Id :");
            System.out.println("3 to Create the User :");
            System.out.println("4 to Update the Existing User :");
            System.out.println("5 Delete the User By Id :");
            System.out.println("6 For Exit......!!!\n");

            int val = sc.nextInt();

            switch (val) {
                   //GetAll------------------------------------------
                case 1:
                    System.out.println(CRUDutil.getAllUser()+"\n");
                    break;

                    //GetById----------------------------------------
                case 2:
                    System.out.println("Enter the UserId");
                    id=sc.nextInt();
                    System.out.println(CRUDutil.getUserById(id)+"\n");
                    break;

                    //Post-------------------------------------------
                case 3:
                    id=sc.nextInt();
                    System.out.println("Enter the name of the User");
                    nameOfUser = sc.next();
                    System.out.println("Enter the Email of the User");
                    email = sc.next();
                    System.out.println("Enter the Gender of the User");
                    gender = sc.next();
                    System.out.println("Enter the Status of the User [Active : 1] or [Inactive : 0]");
                    status = (sc.nextInt() == 1) ? "active" : "inactive";
                    User userToCreate = new User(id,nameOfUser, email, gender, status);
                    System.out.println(CRUDutil.createUser(userToCreate)+"\n");
                    break;

                //Put or Patch---------------------------------------
                case 4:
                    System.out.println("Enter the UserId to Update");
                    id = sc.nextInt();
                    System.out.println("Enter the name of the User");
                    nameOfUser = sc.next();
                    System.out.println("Enter the Email of the User");
                    email = sc.next();
                    System.out.println("Enter the Status of the User [Active : 1] or [Inactive : 0]");
                    status = (sc.nextInt() == 1) ? "active" : "inactive";
                    User userToUpdate= new User(nameOfUser, email, status);
                    System.out.println(CRUDutil.updateUser(id, userToUpdate)+"\n");
                    break;

                //Delete----------------------------------------------
                case 5:
                    System.out.println("Enter the UserId to Delete");
                    id = sc.nextInt();
                    System.out.println(CRUDutil.deletUser(id)+"\n");
                    break;


                //Abort------------------------------------------------
                case 6:
                    return;

                default:
                    System.out.println("Enter the correct option...!!!!!!!\n");
            }
        }
    }
}
