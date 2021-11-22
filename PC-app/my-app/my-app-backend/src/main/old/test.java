package first;

import java.io.IOException;

import com.mitchellbosecke.pebble.extension.Test;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.VerticalBarPlot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.PieTrace;

public class test {
    
    public static void Bar() throws IOException{
        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/10000.csv");
        Plot.show(
            VerticalBarPlot.create(
                "Total app duration",
                table,
                "name",
                Layout.BarMode.GROUP,
                "Duration"));
    
      }

      public static void Bar100000() throws IOException{
        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/100000.csv");
        Plot.show(
            VerticalBarPlot.create(
                "Total app duration",
                table,
                "name",
                Layout.BarMode.GROUP,
                "Duration"));
    
      }

      public static void Bar1000000() throws IOException{
        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/1000000.csv");
        Plot.show(
            VerticalBarPlot.create(
                "Total app duration",
                table,
                "name",
                Layout.BarMode.GROUP,
                "Duration"));
    
      }

      public static void print() throws IOException {

        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/10000.csv");
    
        PieTrace trace =
        PieTrace.builder(table.categoricalColumn("name"), table.numberColumn("Duration")).build();
        Layout layout = Layout.builder().title("Total app duration").build();
    
        Plot.show(new Figure(layout, trace));
    
    
      }

      public static void print2() throws IOException {

        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/100000.csv");
    
        PieTrace trace =
        PieTrace.builder(table.categoricalColumn("name"), table.numberColumn("Duration")).build();
        Layout layout = Layout.builder().title("Total app duration").build();
    
        Plot.show(new Figure(layout, trace));
    
    
      }

      public static void print3() throws IOException {

        Table table = Table.read().csv("C:/Users/F4b/Documents/Productividad/my-app/1000000.csv");
    
        PieTrace trace =
        PieTrace.builder(table.categoricalColumn("name"), table.numberColumn("Duration")).build();
        Layout layout = Layout.builder().title("Total app duration").build();
    
        Plot.show(new Figure(layout, trace));
    
    
      }
    /*
    public static void timelist() throws IOException{
        MyDoubleLinkedList<Double> list = new MyDoubleLinkedList<>();
        MyDoubleLinkedList<String> list1 = new MyDoubleLinkedList<>();
        for (int i = 0; i < 10000;i++){
            list.insertEnd(0.0);
            list1.insertEnd("a");
        } 
        Document.createCSV(list1, list1, list);

    }

    public static void timelist2() throws IOException{
        MyDoubleLinkedList<Double> list = new MyDoubleLinkedList<>();
        MyDoubleLinkedList<String> list1 = new MyDoubleLinkedList<>();
        for (int i = 0; i < 100000;i++){
            list.insertEnd(0.0);
            list1.insertEnd("a");
        } 
        Document.createCSV(list1, list1, list);
    }

    public static void timelist3() throws IOException{
        MyDoubleLinkedList<Double> list = new MyDoubleLinkedList<>();
        MyDoubleLinkedList<String> list1 = new MyDoubleLinkedList<>();
        for (int i = 0; i < 1000000;i++){
                list.insertEnd(0.0);
                list1.insertEnd("a");
        } 
        Document.createCSV(list1, list1, list);
    }
    */


    public static void main(String[] args) throws IOException{
        long s = System.nanoTime();
        //test.timelist3();
        //test.Bar();
        //test.print3();
        long f = System.nanoTime()-s;
        System.out.println(f/1000000000);
        System.out.println(f);
        //test.Bar100000();
        //test.Bar1000000();
    }
} 
