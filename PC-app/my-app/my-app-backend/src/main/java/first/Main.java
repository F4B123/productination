package first;

public class Main {
    
    public static void main(String[] args) {
        init.run();

        //detiene el script lanzado en init.run()
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                init.p.destroy();
            }
        }));
          
    }
}



