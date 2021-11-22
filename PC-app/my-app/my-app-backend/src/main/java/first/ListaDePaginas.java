package first;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ListaDePaginas {
    String nombre="";
    Cola<String> paginas=new Cola<>();
    Bloqueador bloqueador=new Bloqueador();
    ListaDePaginas(String nombre){
        this.nombre=nombre;
        try {
            FileWriter fw=new FileWriter("bloqueadores",true);
            BufferedWriter bw=new BufferedWriter(fw);
            if(!bloqueador.fileToString("bloqueadores").contains(nombre+"\n")){
                bw.write(nombre+"\n");   
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void anadir(String pagina){        
        try {
            FileWriter fw=new FileWriter(nombre,true);
            BufferedWriter bw=new BufferedWriter(fw);
            if(!bloqueador.fileToString(nombre).contains(pagina+"\n")){
                bw.write(pagina+"\n");
            }   
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
    public void bloquear() throws Exception{
        String input = null;
        Scanner sc = new Scanner(new File(nombre));
        while (sc.hasNextLine()) {
            input = sc.nextLine().replace("https://","");
            paginas.enqueue(input);
            bloqueador.anadir(input);
        }
        System.out.println(paginas.toString());
     }
     public void desbloquear() throws Exception{
        if(paginas.estaVacia()){
           Scanner sc = new Scanner(new File(nombre));
           while (sc.hasNextLine()) {
               String input = sc.nextLine();
               paginas.enqueue(input);
           }
        }
       try{
           String contenido=bloqueador.fileToString(bloqueador.sysdir);
           while(!paginas.estaVacia()){
               contenido=contenido.replaceAll("127.0.0.1 "+ paginas.dequeue().toString(),"");
           }
           FileWriter fw=new FileWriter(bloqueador.sysdir);
           BufferedWriter bw=new BufferedWriter(fw);
           bw.write(contenido);
           bw.close();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
     public void pomodoro(int tiempo) throws Exception{
        bloquear();
        Timer timer=new Timer();
        timer.schedule(new Remind(),tiempo*1000*60);
     }
     class Remind extends TimerTask{
         public void run(){
             try {
                desbloquear();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         }
     }

    
}
