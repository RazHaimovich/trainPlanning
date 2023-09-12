package finalTask.entities;

public class TrainConductor extends TrainEmployee {

	public TrainConductor(String id, String name) {
		super(id, name);
	}

	public String toString() {
		return "Train Conductor: " + super.toString();
	}

}
