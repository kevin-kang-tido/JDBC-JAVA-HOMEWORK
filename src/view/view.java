package view;

import controller.UserController;
import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class view {
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
        menu.addCell("     2.Create            ");
        menu.addCell("     3.Update            ");
        menu.addCell("     4.Delete            ");
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




}
