package finalTask.entities;

public class Driver extends TrainEmployee {

	public Driver(String id, String name) {
		super(id, name);
	}

	public String toString() {
		return "Driver: " + super.toString();
	}

}
