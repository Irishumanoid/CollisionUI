package UI.AsteroidsApplication;

public class Available {
    private static AsteroidsApplication asterApp= new AsteroidsApplication();
    public static int numAvailable;

    static {
        numAvailable = asterApp.getNumProjectiles() + 1;
    }
}
