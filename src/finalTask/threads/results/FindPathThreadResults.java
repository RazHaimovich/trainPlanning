package finalTask.threads.results;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Vector;

import finalTask.entities.Station;

public class FindPathThreadResults {
	private Vector<HashMap<Station, LocalTime>> paths;

	public synchronized Vector<HashMap<Station, LocalTime>> getPaths() {
		return paths;
	}

	public synchronized void setPaths(Vector<HashMap<Station, LocalTime>> paths) {
		this.paths = paths;
	}
}
