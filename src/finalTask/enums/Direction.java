package finalTask.enums;

public enum Direction {
	South("South"), North("North");

	String direction;

	Direction(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}
