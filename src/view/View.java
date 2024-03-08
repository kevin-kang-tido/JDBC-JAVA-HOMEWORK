package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class View {
    private final  static UserController userController  = new UserController();
    public static int ui() {

        Table menu = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        System.out.println("""
                
                     ▄█ ████████▄  ▀█████████▄   ▄████████         ▄█    █▄     ▄██████▄    ▄▄▄▄███▄▄▄▄      ▄████████  ▄█     █▄   ▄██████▄     ▄████████    ▄█   ▄█▄\s
                    ███ ███   ▀███   ███    ███ ███    ███        ███    ███   ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███ ███     ███ ███    ███   ███    ███   ███ ▄███▀\s
                    ███ ███    ███   ███    ███ ███    █▀         ███    ███   ███    ███ ███   ███   ███   ███    █▀  ███     ███ ███    ███   ███    ███   ███▐██▀  \s
                    ███ ███    ███  ▄███▄▄▄██▀  ███              ▄███▄▄▄▄███▄▄ ███    ███ ███   ███   ███  ▄███▄▄▄     ███     ███ ███    ███  ▄███▄▄▄▄██▀  ▄█████▀   \s
                    ███ ███    ███ ▀▀███▀▀▀██▄  ███             ▀▀███▀▀▀▀███▀  ███    ███ ███   ███   ███ ▀▀███▀▀▀     ███     ███ ███    ███ ▀▀███▀▀▀▀▀   ▀▀█████▄   \s
                    ███ ███    ███   ███    ██▄ ███    █▄         ███    ███   ███    ███ ███   ███   ███   ███    █▄  ███     ███ ███    ███ ▀███████████   ███▐██▄  \s
                    ███ ███   ▄███   ███    ███ ███    ███        ███    ███   ███    ███ ███   ███   ███   ███    ███ ███ ▄█▄ ███ ███    ███   ███    ███   ███ ▀███▄\s
                █▄ ▄███ ████████▀  ▄█████████▀  ████████▀         ███    █▀     ▀██████▀   ▀█   ███   █▀    ██████████  ▀███▀███▀   ▀██████▀    ███    ███   ███   ▀█▀\s
                ▀▀▀▀▀▀                                                                                                                          ███    ███   ▀        \s
                                
                """);
        System.out.println("==================  Menu ==============================");
        menu.addCell("     1.Read Data         ");
        menu.addCell(" 2.Search User BY Id     ");
        menu.addCell("     3.Create            ");
        menu.addCell("     4.Update            ");
        menu.addCell("     5.Delete            ");
        System.out.println(menu.render());

        System.out.println("====== Choose your Option: ");
        return new Scanner(System.in).nextInt();
    }
    // ui for option 1
    public static void readAllUser(){
        try {
            Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            List<User> users = userController.getAllUser();

            table.addCell("  USER ID  ");
            table.addCell("  USER UUID  ");
            table.addCell("     USER NAME     ");
            table.addCell("     USER EMAIL     ");
            table.addCell("     USER PASSWORD     ");
            table.addCell("     USER IS DELETED     ");
            table.addCell("     USER IS VERIFY     ");
            for (User user : users) {
                table.addCell(String.valueOf(user.getUser_id()), cellStyle);
                table.addCell(user.getUser_uuid(), cellStyle);
                table.addCell(user.getUser_name(), cellStyle);
                table.addCell(user.getUser_email(), cellStyle);
                table.addCell(user.getUser_password(), cellStyle);
                table.addCell(String.valueOf(user.getIs_deleted()), cellStyle);
                table.addCell(String.valueOf(user.getIs_verified()), cellStyle);
            }
            System.out.println(table.render());
        }catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
    public static void  searchUI(){
        try{
            System.out.print(">>>>> Enter User ID: ");
            Integer searchID = new Scanner(System.in).nextInt();
            Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            List<User> users = Collections.singletonList(userController.searchUserBYId(searchID));

            table.addCell("  USER ID  ");
            table.addCell("  USER UUID  ");
            table.addCell("     USER NAME     ");
            table.addCell("     USER EMAIL     ");
            table.addCell("     USER PASSWORD     ");
            table.addCell("     USER IS DELETED     ");
            table.addCell("     USER IS VERIFY     ");
            for (User user : users) {
                table.addCell(String.valueOf(user.getUser_id()), cellStyle);
                table.addCell(user.getUser_uuid(), cellStyle);
                table.addCell(user.getUser_name(), cellStyle);
                table.addCell(user.getUser_email(), cellStyle);
                table.addCell(user.getUser_password(), cellStyle);
                table.addCell(String.valueOf(user.getIs_deleted()), cellStyle);
                table.addCell(String.valueOf(user.getIs_verified()), cellStyle);
            }
            System.out.println(table.render());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    // Create user

        public  User CreateUser(){
            Scanner scanner = new Scanner(System.in);

            User createNewUser = new User();
            Random random = new Random();
            UUID uuid = UUID.randomUUID();
            String createNewUUID = uuid.toString().substring(0,9);

            try{

                System.out.println("Enter Username : ");
                createNewUser.setUser_name(scanner.nextLine());
                System.out.println("Enter User Email: ");
                createNewUser.setUser_email(scanner.nextLine());
                System.out.println("Enter User Password : ");
                createNewUser.setUser_password(scanner.nextLine());
                System.out.println("Enter Verify (Ture/False):");
                createNewUser.setIs_verified(Boolean.parseBoolean(scanner.nextLine()));

                createNewUser.setIs_deleted(false);
                createNewUser.setUser_uuid(createNewUUID);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return createNewUser;
        }

        // Delete User
        public User deleteUser(List<User> users){
            System.out.print("Enter id to delete: ");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());
            for (User user : users) {
                if(user.getUser_id().equals(id)){
                    users.remove(user);
                    return users.getFirst();
                }
            }
          return null;

        }
        public static int updateUser(List<User> userList){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Input User Id you want to delete: ");
            int inputId = Integer.parseInt(new Scanner(System.in).nextLine());
            for (User user : userList){
                if ( user.getUser_id().equals(inputId)){
                    System.out.println("Enter Username : ");
                    user.setUser_name(scanner.nextLine());
                    System.out.println("Enter User Email: ");
                    user.setUser_email(scanner.nextLine());
                    System.out.println("Enter User Password : ");
                    user.setUser_password(scanner.nextLine());
                    System.out.println("Enter Verify (Ture/False):");
                    user.setIs_verified(Boolean.parseBoolean(scanner.nextLine()));

                    user.setIs_deleted(false);

                }
                return inputId;
            }
        return -1;
        }









}
