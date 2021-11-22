package first;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import tech.tablesaw.api.Table;


public class Document {


    public static void createCSV(MyDoubleLinkedList<Appinfo> list) throws IOException{
        
        FileWriter writer = new FileWriter("test.csv");
        StringBuilder sb = new StringBuilder();
        writer.append("name");
        writer.append(',');
        writer.append("start");
        writer.append(','); 
        writer.append("Duration"); 
        writer.append('\n');
        for(int i = 0; i< list.getCount()-1; i++){
            long convert = TimeUnit.SECONDS.convert(list.readElement(i).duration, TimeUnit.NANOSECONDS);
            long convert2 = TimeUnit.SECONDS.convert(list.readElement(i).start, TimeUnit.NANOSECONDS);
            long convert3 = TimeUnit.SECONDS.convert(list.readElement(i).end, TimeUnit.NANOSECONDS);
            if(sb.length() != 0){
                sb.append("\n");
            }
            sb.append(list.readElement(i).name);
            sb.append(',');
            sb.append(convert2);
            sb.append(',');
            sb.append(convert3);
            sb.append(',');
            try{
                sb.append((convert));
            }
            catch(NullPointerException e){
                sb.append(0.0);
            }
        }
        writer.append(sb);
        writer.flush();
        writer.close();

        //Table bushTable = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/test.csv");
        //bushTable = bushTable.sortOn("name");

        
        //plot.print();
        //plot.UpdateCsv(bushTable);

    }



}
