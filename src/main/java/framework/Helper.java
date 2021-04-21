package framework;

public class Helper {
    
    public static int getRandomInteger() {
        return (int) (Math.random() * 1000);
    }
    
    public static String generateTitle() {
        return "Katarina-" + getRandomInteger();
    }
    
}
