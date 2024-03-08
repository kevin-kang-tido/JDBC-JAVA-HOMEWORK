import controller.UserController;
import view.View;

import static view.View.*;


public class Main {
    private final static UserController userController  = new UserController();
    public static void main(String[] args) {

      while (true) {
          try {
          switch (View.ui()) {
              //read
              case 1 -> {
                  readAllUser();
              }
              // search
              case 2 -> {
                  searchUI();
              }
              // create
              case 3 -> {
                  userController.createUser();
              }
              // Update by id
              case 4 -> {
                  userController.updateUser();
              }
              // Delete user
              case 5 -> {
                    userController.deleteUser();
              }
              default -> System.out.println("No option please Choose again!!!");

          }
          }catch (Exception e){
              System.out.println(e.getMessage());
          }
      }

    }

}