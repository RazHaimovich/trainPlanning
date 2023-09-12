package finalTask.entities;

public class Train {
	private int id;

	public Train(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String toString() {
		return "Train Id: " + this.getId();
	}
}
