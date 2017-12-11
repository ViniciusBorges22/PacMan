package elements;

public class Cherry extends Fruit implements Runnable {

    public Cherry() {
        super("cherry.png", 1);
        this.points = 100;
        this.duration = 400;
    }

    @Override
    public void run() {
        
    }
    
    
    
}
