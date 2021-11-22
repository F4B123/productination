package old;

public class Appinfo {
    Long duration = (long) 0;
    String name;
    Long pid;
    Long start;
    Long end;

    public Appinfo(Long pid,String name,Double totaltime){
        this.pid = pid;
        this.start = System.nanoTime();
        this.end = System.nanoTime();
        this.duration += end-start;
        this.name = name;
        
    }

    public void update(){ ///TODO: problemas 
        end = System.nanoTime();
        duration += end-start;
        
    }
}
