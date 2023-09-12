package finalTask.menuActions;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

import finalTask.Storge;
import finalTask.entities.Route;
import finalTask.entities.Station;
import finalTask.interfaces.MenuAction;
import finalTask.utils.PathUtils;
import finalTask.utils.RouteUtils;

public class PrintAllPaths implements MenuAction {

	@Override
	public void action(Scanner scanner, Storge storge) {

		for (Route r : storge.getRoutes()) {
			HashMap<Station, LocalTime> p = RouteUtils.getPath(r);
			if(p != null) PathUtils.printPath(p, r.getStartStation());
		}
	}

}
