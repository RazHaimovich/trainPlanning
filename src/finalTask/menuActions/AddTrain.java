package finalTask.menuActions;

import java.util.Scanner;
import java.util.Vector;

import finalTask.Storge;
import finalTask.entities.Train;
import finalTask.interfaces.MenuAction;

public class AddTrain implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) {
		Vector<Train> trains = storge.getTrains();
		int finalTrainNumber = -1;

		while (finalTrainNumber == -1) {
			System.out.println("Choose Train Number(Id):");
			int trainNumber = scanner.nextInt();
			if(trainNumber == -1) return;
			if (!trainExists(trainNumber, trains)) {
				finalTrainNumber = trainNumber;
			}
		}

		trains.add(new Train(finalTrainNumber));
	}

	private boolean trainExists(int trainNumber, Vector<Train> trains) {
		for (Train t : trains) {
			if (t.getId() == trainNumber) {
				return true;
			}
		}
		return false;
	}

}
