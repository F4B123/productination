package first;
import java.util.stream.Stream;
import java.io.IOException;
import java.lang.ProcessHandle;
import java.text.SimpleDateFormat;  
import java.util.Date;  


class Data {

    static Double totaltime = (double) 0.1;
    MyDoubleLinkedList<Appinfo> list;

    public Data(){
        list = new MyDoubleLinkedList<>();
    }
   
    public static void getData(MyDoubleLinkedList<Appinfo> list){
        Stream<ProcessHandle> liveProcesses = ProcessHandle.allProcesses();
        liveProcesses.filter(ProcessHandle::isAlive)
            .forEach(ph -> {
                String program = ph.info().command().toString();
                long pid = ph.pid();
                String[] splitString = program.split("\\\\");
                program = splitString[splitString.length-1];
                String[] splitString2 = program.split("\\.");
                program = splitString2[0];
                
                Appinfo aplication = new Appinfo( pid ,program, Data.totaltime);
                list.insertEnd(aplication);                
                
        });     
    }



    public static void filter(MyDoubleLinkedList<Appinfo> list){
        for (int i = 0; i< list.getCount()-1;i++){ //ya no se tienen los procesos repetidos
            //System.out.println(list.readElement(i).name);
               for (int j = i+1; j< list.getCount()-1; j++){
                    if ( list.readElement(i).name.equals(list.readElement(j).name)){
                        list.delete(j);
                        j--;
                    }
               }        
        }
    }

    public void print(){
        System.out.println(list.toString());
    }
  

    public void startMethod(){
        long start = System.nanoTime();
        for (int i = 0; i < list.getCount(); i++){
            Appinfo element = list.readElement(i);
            element.start= start;
        }
    }

    public void update() throws IOException{
        Data aux = new Data();        
        Data.getData(aux.list);
        Data.filter(aux.list);
/////--------------------Agrega nuevos a la lista original y actualizar los que siguen------------------------------------------------------        
        for (int i = 0; i < aux.list.getCount(); i++){
            boolean isIn = false;
            String name = aux.list.readElement(i).name;

            for (int j = 0; j <list.getCount(); j++){               
                Appinfo element = list.readElement(j);
                String name2 = element.name;
                if(name.equals(name2)){
                    element.update();
                    isIn = true;
                    break;
                }
            } 
            if(isIn == false){
                list.insertEnd(aux.list.readElement(i));
            }
            System.out.println(aux.list.readElement(i).name + aux.list.readElement(i).duration);                                   
        }
        startMethod();
        //
        
        /////----------------------------------------------------------
        
    }
    


}