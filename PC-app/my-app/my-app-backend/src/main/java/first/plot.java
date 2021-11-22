package first;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.VerticalBarPlot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Page;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.PieTrace;


public class plot extends Plot{

  
  /*
  //------------------------------------------------------------------------ Machetazo =(
  se trajo de la superclase
  */
  public static void show(Figure figure, String divName, File outputFile) {
    Page page = Page.pageBuilder(figure, divName).build();
    String output = page.asJavascript();


    try {
      try (Writer writer =
          new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
        writer.write(output);
      }
      //new Browser().browse(outputFile);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static void show(String html, File outputFile) {
    try {
      try (Writer writer =
          new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8)) {
        writer.write(html);
      }
      //new Browser().browse(outputFile);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static void show(Figure figure, String divName) {
    show(figure, divName, defaultFile());
  }

  public static void show(Figure figure) {
    show(figure, randomFile());
  }

  public static void show(Figure figure, File outputFile) {
    show(figure, DEFAULT_DIV_NAME, outputFile);
  }



//------------------------------------------------------------------------
  static Table table;

  public static void Bar() throws IOException{
    Table table = Table.read().csv("C:/Users/F4B/Documents/Productividad/csvdata1.csv");    
    show(
        VerticalBarPlot.create(
            "Total app duration",
            table,
            "name",
            Layout.BarMode.GROUP,
            "Duration"));

  }

  public static void print() throws IOException {

    Table table = Table.read().csv("C:/Users/F4B/Documents/Productividad/csvdata1.csv");

    PieTrace trace =
    PieTrace.builder(table.categoricalColumn("name"), table.numberColumn("Duration")).build();
    Layout layout = Layout.builder().title("Total app duration").build();


    show(new Figure(layout, trace));
   
  }


}