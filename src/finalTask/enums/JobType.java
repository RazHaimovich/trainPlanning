package finalTask.enums;

public enum JobType {
	TrainConductor("Train Conductor"), StationManager("Station Manager"), Driver("Driver");

	private final String displayName;

	JobType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static JobType fromDisplayName(String displayName) {
		for (JobType type : JobType.values()) {
			if (type.getDisplayName().equals(displayName)) {
				return type;
			}
		}
		throw new IllegalArgumentException("No enum constant with displayName " + displayName);
	}
}
