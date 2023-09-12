package finalTask.entities;

import java.util.Vector;

public abstract class TrainEmployee extends Employee {

	private Vector<Route> routes = new Vector<Route>();

	public TrainEmployee(String id, String name) {
		super(id, name);
	}

	public void addRoute(Route route) {
		routes.add(route);
	}

}
