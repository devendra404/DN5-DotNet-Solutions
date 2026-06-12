public class Main {

    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First Message");
        logger2.log("Second Message");

        if (logger1 == logger2) {
            System.out.println("Only One Instance Exists");
        } else {
            System.out.println("Multiple Instances Exist");
        }
    }
}