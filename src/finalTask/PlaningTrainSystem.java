package finalTask;

import java.util.HashMap;
import java.util.Scanner;

import finalTask.interfaces.MenuAction;
import finalTask.menuActions.*;
import finalTask.utils.TerminalColors;

public class PlaningTrainSystem {

	private Scanner scanner;
	private Storge storge;

	public void runSystem() {
		this.scanner = new Scanner(System.in);
		this.storge = new Storge();
		runMenu();
	}

	private void runMenu() {
		while (true) {
			System.out.println("\n----------Choose An action----------");
			System.out.println("\t1) Print all data");
			System.out.println("\t2) Add a employee (GUI)");
			System.out.println("\t3) Add a station");
			System.out.println("\t4) Add a train");
			System.out.println("\t5) Add a route");
			System.out.println("\t6) Find paths");

			System.out.println("\t0) Exit");
			int choosenOption = scanner.nextInt();
			scanner.nextLine();
			handleAction(choosenOption);
		}
	}

	private void handleAction(int action) {
		HashMap<Integer, MenuAction> actions = new HashMap<>();
		actions.put(0, new CloseProgram());
		actions.put(1, new PrintAllData());
		actions.put(2, new AddEmployee());
		actions.put(3, new AddStation());
		actions.put(4, new AddTrain());
		actions.put(5, new AddRoute());
		actions.put(6, new FindPaths());
		actions.put(8, new PrintAllPaths());
		actions.put(9, new GenerateFakeData());

		MenuAction selectedAction = actions.get(action);
		if (selectedAction != null) {
			try {
				if (action > 2 && action < 7)
					System.out.println(TerminalColors.GREEN
							+ "You can go back to the main menu at any step by typing -1" + TerminalColors.RESET);
				selectedAction.action(scanner, storge);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
