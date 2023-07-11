
public class processCommands {
	 private static int roomWidth;
	    private static int roomHeight;
	    private static int carX;
	    private static int carY;
	    private static String carDirection;
	    
	private static boolean processCommands(String commands) {
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            switch (command) {
                case 'F':
                    if (!moveForward()) {
                        return false;
                    }
                    break;
                case 'B':
                    if (!moveBackward()) {
                        return false;
                    }
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                default:
                    System.out.println("Invalid command: " + command);
                    return false;
            }
        }
        return true;
    }

    private static boolean moveForward() {
        int newX = carX;
        int newY = carY;

        switch (carDirection) {
            case "N":
                newY++;
                break;
            case "W":
                newX--;
                break;
            case "S":
                newY--;
                break;
            case "E":
                newX++;
                break;
        }

        if (isValidPosition(newX, newY)) {
            carX = newX;
            carY = newY;
            return true;
        } else {
            return false;
        }
    }

    private static boolean moveBackward() {
        int newX = carX;
        int newY = carY;

        switch (carDirection) {
            case "N":
                newY--;
                break;
            case "W":
                newX++;
                break;
            case "S":
                newY++;
                break;
            case "E":
                newX--;
                break;
        }

        if (isValidPosition(newX, newY)) {
            carX = newX;
            carY = newY;
            return true;
        } else {
            return false;
        }
    }

    private static void turnLeft() {
        switch (carDirection) {
            case "N":
                carDirection = "W";
                break;
            case "W":
                carDirection = "S";
                break;
            case "S":
                carDirection = "E";
                break;
            case "E":
                carDirection = "N";
                break;
        }
    }

    private static void turnRight() {
        switch (carDirection) {
            case "N":
                carDirection = "E";
                break;
            case "W":
                carDirection = "N";
                break;
            case "S":
                carDirection = "W";
                break;
            case "E":
                carDirection = "S";
                break;
        }
    }
    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < roomWidth && y >= 0 && y < roomHeight;
    }
	
}
