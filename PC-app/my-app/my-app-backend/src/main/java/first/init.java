package first;

import java.io.IOException;


public class init {

    public static Process p;

    public static void run(){
          
        try {
            // Python interpreter and Data.py location, si se quiere usar debe poner la localizacion del interprete y del script
            ProcessBuilder w = new ProcessBuilder("C:\\Users\\F4B\\AppData\\Local\\Programs\\Python\\Python39\\python.exe", "C:\\Users\\F4B\\Documents\\Productividad\\PC-app-F4B123-patch-1\\my-app\\my-app-backend\\src\\main\\python\\Data.py");
            p = w.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        App.main(null);         //new GUI
        //GUI1.main();

        
        
    }
}
