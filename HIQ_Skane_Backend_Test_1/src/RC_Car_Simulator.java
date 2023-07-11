import java.util.Scanner;

public class RC_Car_Simulator {

	

	static String roomWidth;
	static String roomHeight;
	static int intRoomWidth;
	static int intRoomHeight;
    static int carX;
	static int carY;
	static String carDirection;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		boolean validDimensions = false;
		

		// Get room dimensions and validate user input.
		do {

			System.out.print("Enter room width (in meters): ");
			roomWidth = scanner.nextLine();

			try {
				Integer.parseInt(roomWidth);
				validDimensions = true;

			} catch (Exception e) {
				System.out.println("You entered wrong values, please enter whole meters and have no decimals or letters.");
			}
		} while (validDimensions == false);

		do {
			validDimensions = false;
			System.out.print("Enter room height (in meters): ");
			roomHeight = scanner.nextLine();

			try {
				Integer.parseInt(roomHeight);
				validDimensions = true;

			} catch (Exception e) {
				System.out.println("You entered wrong values, please enter whole meters and have no decimals or letters.");

			}
		} while (validDimensions == false);
		
		// Get initial car position and validate user input.
		boolean validPosition = false;
		do {
			validPosition = false;
			
			try {
				System.out.print("Enter car's initial X coordinate: ");
				carX = Integer.parseInt( scanner.nextLine());
				System.out.print("Enter car's initial Y coordinate: ");
				carY = Integer.parseInt( scanner.nextLine());
				validPosition = true;

			} catch (Exception e) {
				System.out.println("You entered wrong values, please enter digits only in coordinations.");
			} 
		} while (validPosition == false);
		
		// Get initial car direction and validate user input.		
		boolean validDirection = false;
		do {
			System.out.print("Enter car's initial direction (N, W, S, E): ");
			carDirection = scanner.next().toUpperCase();
			if(carDirection.equalsIgnoreCase("N") || carDirection.equalsIgnoreCase("W") || carDirection.equalsIgnoreCase("S") || carDirection.equalsIgnoreCase("E"))
			{
				validDirection = true;				
			}else
			{
				System.out.println("You entered wrong values, you may only enter one of the following values (N, W, S, E).");
			}
			
		} while (validDirection == false);
		
		
		//Get action commands and validate user input the process action commands.


		
		String commands;
		boolean validCommand = false;
		
		do {
			System.out.print("Enter action commands(F, B, L, R): ");
			commands = scanner.next().toUpperCase();

			
			if(commands.equalsIgnoreCase("F") || commands.equalsIgnoreCase("B") || commands.equalsIgnoreCase("L") || commands.equalsIgnoreCase("R"))
			{
				validCommand = true;				
			}else
			{
				System.out.println("You entered wrong values, you may only enter one of the following values (F, B, L, R).");
			}
			
		} while (validCommand == false);
		
		boolean success = processCommands(commands);
		scanner.close();

		// Output simulation result
		if (success) {
			System.out.println("Simulation successful.");
			System.out.println("Car's final position: (" + carX + ", " + carY + ")");
			System.out.println("Car's heading: " + carDirection);
		} else {
			System.out.println("Simulation unsuccessful. Car crashed into a wall.");
		}

	}

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
		intRoomWidth  = Integer.parseInt(roomWidth);
		intRoomHeight = Integer.parseInt(roomHeight);
		return x >= 0 && x < intRoomWidth && y >= 0 && y < intRoomHeight;
	}
}
