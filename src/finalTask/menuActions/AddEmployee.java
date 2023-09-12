package finalTask.menuActions;

import java.util.Scanner;

import finalTask.Storge;
import finalTask.gui.AddEmployeeForm;
import finalTask.interfaces.MenuAction;

public class AddEmployee implements MenuAction {

	private AddEmployeeForm addEmployeeForm = null;

	@Override
	public void action(Scanner scanner, Storge storge) {
		if (addEmployeeForm == null)
			addEmployeeForm = new AddEmployeeForm(null, storge);
		else
			addEmployeeForm.reInitDialog();
		
		addEmployeeForm.close();
	}
}
