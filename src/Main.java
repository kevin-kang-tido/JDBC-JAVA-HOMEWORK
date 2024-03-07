import controller.UserController;
import view.view;

import java.util.Scanner;

import static view.view.readAllUser;
import static view.view.searchUI;


public class Main {
    private final static UserController userController  = new UserController();
    public static void main(String[] args) {

       while(true){
           switch (view.ui()){
               //read
               case 1->{
                   readAllUser();
               }
               // search
               case 2 -> {
                   searchUI();

               }
               // create
               case 3-> {

               }
               // Update
               case 4 -> {

               }
               default -> System.out.println("No option please Choose again!!!");

           }
       }
    }
}