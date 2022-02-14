package IDGenerator;

public final class Generator {
	private static Generator generator = new Generator();
    private int counter = 1;

    private Generator() {
    }

    public static int getID() throws Exception {
        if (generator.counter > 999999) {
            throw new Exception();
        }
        return generator.counter++;
    }

}//class Generator
