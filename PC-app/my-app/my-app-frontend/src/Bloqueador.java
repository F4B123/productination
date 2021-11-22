import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bloqueador {
    String sysdir = System.getenv("WINDIR") + "\\system32\\drivers\\etc\\hosts";
    Bloqueador(){
        
    }
    public void anadir(String pagina){        
        try {
            FileWriter fw=new FileWriter(sysdir,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("\n127.0.0.1 " + pagina);   
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    public void eliminar(String pagina) throws Exception{
        try {
            String contenido=fileToString(sysdir);
            contenido=contenido.replaceAll("127.0.0.1 "+ pagina,"");
            FileWriter fw=new FileWriter(sysdir);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public String fileToString(String filePath) throws Exception{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           input = input+"\n";
           sb.append(input);
        }
        return sb.toString();
     }
}
