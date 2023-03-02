public class Start {
    public static void main(String[] args) {
        int rows, cols;
        try {
            if (args.length == 2) {
                rows = Integer.parseInt(args[0]);
                cols = Integer.parseInt(args[1]);
            } else {
                System.out.println("No arguments provided. Creating default grid: 30x30");
                rows = 30;
                cols = 30;
            }

            GameOfLifeGUI game = new GameOfLifeGUI(rows, cols);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments. Please provide two integer arguments: <rows> <columns>");
        }
    }
}
