package finalTask.menuActions;

import java.util.Scanner;

import finalTask.Storge;
import finalTask.interfaces.MenuAction;

public class CloseProgram implements MenuAction{

	public void action(Scanner scanner, Storge storge) throws Exception {
		System.exit(0);
	}

}
