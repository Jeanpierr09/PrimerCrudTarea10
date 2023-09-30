package edu;

import edu.database.clsConnection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        clsConnection cone = new clsConnection();

        try{
//        cone.insert();
          cone.read();
//        cone.delete();
//        cone.update();
//        cone.read();




            System.out.printf("Sin ningun error");
        }catch (Exception e){
            System.out.println("Se detectaron errores: " + e);

        }

    }
}