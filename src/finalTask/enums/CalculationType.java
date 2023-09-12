package finalTask.enums;

public enum CalculationType {
	ARRIVAL_TIME_BASED("ARRIVAL_TIME_BASED"), DEPARTURE_TIME_BASED("DEPARTURE_TIME_BASED");

	String calculationType;

	CalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public String getCalculationType() {
		return calculationType;
	}
}
