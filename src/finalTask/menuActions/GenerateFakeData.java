package finalTask.menuActions;

import java.util.Scanner;
import java.util.Vector;

import finalTask.Storge;
import finalTask.entities.Driver;
import finalTask.entities.Station;
import finalTask.entities.Train;
import finalTask.entities.TrainConductor;
import finalTask.entities.StationManager;
import finalTask.interfaces.MenuAction;

public class GenerateFakeData implements MenuAction {
	private Storge storge;

	@Override
	public void action(Scanner scanner, Storge storge) {
		this.storge = storge;
		generateFakeData();
	}

	private void generateFakeData() {
		Vector<Station> stations = storge.getStations();
		Vector<StationManager> stationManagers = storge.getStationManagers();
		Vector<Driver> drivers = storge.getDrivers();
		Vector<TrainConductor> trainConductors = storge.getTrainConductor();
		Vector<Train> trains = storge.getTrains();

		// Generate Station Managers Data
		generateStationManagers(stationManagers);

		// Generate Stations Data
		generateStations(stations, stationManagers);

		// Generate Drivers
		generateDrivers(drivers);

		// Generate Train Conductors
		generateConductors(trainConductors);

		// Generate Trains Data
		generateTrains(trains);
	}

	private void generateStationManagers(Vector<StationManager> stationManagers) {
		String[][] stationManagerData = { { "100000001", "Raz" }, { "100000002", "Ben" }, { "100000003", "Barak" },
				{ "100000004", "Orel" }, { "100000005", "Shir" }, { "100000006", "Dan" }, { "100000007", "Tamar" },
				{ "100000008", "Amit" }, { "100000009", "Yael" }, { "100000010", "Ido" }, { "100000011", "Adi" },
				{ "100000012", "Or" }, { "100000013", "Liat" }, { "100000014", "Shai" }, { "100000015", "Maya" },
				{ "100000016", "Ron" }, { "100000017", "Dana" }, { "100000018", "Nir" }, { "100000019", "Noa" },
				{ "100000020", "Eitan" }, { "100000021", "Hila" }, { "100000022", "Yotam" }, { "100000023", "Rona" },
				{ "100000024", "Tomer" }, { "100000025", "Gal" }, { "100000026", "Itay" }, { "100000027", "Sarit" },
				{ "100000028", "Dor" }, { "100000029", "Liron" }, { "100000030", "Yair" }, { "100000031", "Mor" },
				{ "100000032", "Eyal" }, { "100000033", "Michal" }, { "100000034", "Aviv" } };

		for (String[] data : stationManagerData) {
			StationManager sm = new StationManager(data[0], data[1]);
			stationManagers.add(sm);
		}
	}

	private void generateStations(Vector<Station> stations, Vector<StationManager> stationManagers) {
		Object[][] stationData = { { 1, "Nahariyya", stationManagers.get(0) }, { 2, "Akko", stationManagers.get(1) },
				{ 3, "Kiryat Motzkin", stationManagers.get(2) }, { 4, "Kiryat Haim", stationManagers.get(3) },
				{ 5, "Hutzot Hamifratz", stationManagers.get(4) }, { 6, "Merkazit Hamifratz", stationManagers.get(5) },
				{ 7, "Merkaz Hashmona", stationManagers.get(6) }, { 8, "Bat Galim", stationManagers.get(7) },
				{ 9, "Hof HaCarmel", stationManagers.get(8) }, { 10, "Atlit", stationManagers.get(9) },
				{ 11, "Binyamina", stationManagers.get(10) },
				{ 12, "Caesarea - Pardes Hanna", stationManagers.get(11) },
				{ 13, "Hadera West", stationManagers.get(12) }, { 14, "Netanya", stationManagers.get(13) },
				{ 15, "Netanya Sapir", stationManagers.get(14) }, { 16, "Beit Yehoshua", stationManagers.get(15) },
				{ 17, "Herzliya", stationManagers.get(16) }, { 18, "Tel Aviv University", stationManagers.get(17) },
				{ 19, "Tel Aviv Savidor Center", stationManagers.get(18) },
				{ 20, "Tel Aviv HaShalom", stationManagers.get(19) },
				{ 21, "Tel Aviv HaHagana", stationManagers.get(20) }, { 22, "Holon Junction", stationManagers.get(21) },
				{ 23, "Holon Wolfson", stationManagers.get(22) }, { 24, "Bat Yam-Yoseftal", stationManagers.get(23) },
				{ 25, "Bat Yam-Komemiyut", stationManagers.get(24) },
				{ 26, "Rishon LeTsiyon - Moshe Dayan", stationManagers.get(25) },
				{ 27, "Yavne West", stationManagers.get(26) }, { 28, "Ashdod Ad Halom", stationManagers.get(27) },
				{ 29, "Ashkelon", stationManagers.get(28) }, { 30, "Sderot", stationManagers.get(29) },
				{ 31, "Netivot", stationManagers.get(30) }, { 32, "Ofakim", stationManagers.get(31) },
				{ 33, "Be'er Sheva North", stationManagers.get(32) },
				{ 34, "Be'er Sheva Center", stationManagers.get(33) } };

		Station previousStation = null;
		int[] distances = { -1, // none
				7, // Nahariya to Akko
				10, // Akko to Kiryat Motzkin
				3, // Kiryat Motzkin to Kiryat Haim
				4, // Kiryat Haim to Hutzut Hamifratz
				5, // Hutzut Hamifratz to Merkazit Hamifratz
				8, // Merkazit Hamifratz to Merkaz HaShmona
				5, // Merkaz HaShmona to Bat Galim
				6, // Bat Galim to Huf HaCarmel
				9, // Huf HaCarmel to Atlit
				13, // Atlit to Binyamina
				4, // Binyamina to Caesarea-Pardes Hanna
				6, // Caesarea-Pardes Hanna to Hadera Maarav
				10, // Hadera Maarav to Netanya
				5, // Netanya to Netanya Sapir
				3, // Netanya Sapir to Beit Yehoshua
				8, // Beit Yehoshua to Herzliya
				6, // Herzliya to Tel Aviv University
				4, // Tel Aviv University to Tel Aviv Savidor Center
				3, // Tel Aviv Savidor Center to Tel Aviv HaShalom
				4, // Tel Aviv HaShalom to Tel Aviv HaHagana
				7, // Tel Aviv HaHagana to Holon Junction
				5, // Holon Junction to Holon Wolfson
				5, // Holon Wolfson to Bat Yam-Yoseftal
				4, // Bat Yam-Yoseftal to Bat Yam-Komemiyut
				9, // Bat Yam-Komemiyut to Rishon LeTsiyon - Moshe Dayan
				10, // Rishon LeTsiyon - Moshe Dayan to Yavne West
				12, // Yavne West to Ashdod Ad Halom
				12, // Ashdod Ad Halom to Ashkelon
				10, // Ashkelon to Sderot
				9, // Sderot to Netivot
				10, // Netivot to Ofakim
				15, // Ofakim to Be'er Sheva North
				6 // Be'er Sheva North to Be'er Sheva Center
		};

		for (int i = 0; i < stationData.length; i++) {
			Station st = new Station((int) stationData[i][0], (String) stationData[i][1],
					(StationManager) stationData[i][2]);
			if (previousStation != null) {
				st.setNorthernStation(previousStation, distances[i]);
			}
			stations.add(st);
			previousStation = st;
		}
	}

	private void generateDrivers(Vector<Driver> drivers) {
		String[][] driversData = { { "200000001", "Avi" }, { "200000002", "Itzak" }, { "200000003", "Jacob" },
				{ "200000004", "Moses" }, { "200000005", "Elijah" }, { "200000006", "Samuel" },
				{ "200000007", "David" }, { "200000008", "Aaron" }, { "200000009", "Joseph" },
				{ "200000010", "Daniel" }, { "200000011", "Noam" }, { "200000012", "Asher" }, { "200000013", "Amir" },
				{ "200000014", "Oded" }, { "200000015", "Yossi" }, { "200000016", "Ronen" }, { "200000017", "Lior" },
				{ "200000018", "Yehuda" }, { "200000019", "Sarah" }, { "200000020", "Rebecca" },
				{ "200000021", "Ruth" }, { "200000022", "Rachel" }, { "200000023", "Hannah" },
				{ "200000024", "Miriam" }, { "200000025", "Lea" }, { "200000026", "Leah" }, { "200000027", "Esther" },
				{ "200000028", "Shoshana" }, { "200000029", "Dina" }, { "200000030", "Avigail" },
				{ "200000031", "Yaela" }, { "200000032", "Yitzhak" }, { "200000033", "Rivka" },
				{ "200000034", "Shlomo" } };

		for (String[] data : driversData) {
			Driver driver = new Driver(data[0], data[1]);
			drivers.add(driver);
		}
	}

	private void generateConductors(Vector<TrainConductor> trainConductors) {
		String[][] conductorsData = { { "300000001", "Aaron" }, { "300000002", "David" }, { "300000003", "Solomon" },
				{ "300000004", "Isaiah" }, { "300000005", "Jeremiah" }, { "300000006", "Ezekiel" },
				{ "300000007", "Talia" }, { "300000008", "Malka" }, { "300000009", "Noa" }, { "300000010", "Sarah" },
				{ "300000011", "Hadassah" }, { "300000012", "Benjamin" }, { "300000013", "Lior" },
				{ "300000014", "Gideon" }, { "300000015", "Eliezer" }, { "300000016", "Dvorah" },
				{ "300000017", "Rotem" }, { "300000018", "Maayan" }, { "300000019", "Oz" }, { "300000020", "Ayelet" },
				{ "300000021", "Dana" }, { "300000022", "Shani" }, { "300000023", "Zev" }, { "300000024", "Elad" },
				{ "300000025", "Yonatan" }, { "300000026", "Tal" }, { "300000027", "Nava" }, { "300000028", "Rinat" },
				{ "300000029", "Sivan" }, { "300000030", "Eli" }, { "300000031", "Neta" }, { "300000032", "Omer" },
				{ "300000033", "Alon" }, { "300000034", "Maya" } };

		for (String[] data : conductorsData) {
			TrainConductor conductor = new TrainConductor(data[0], data[1]);
			trainConductors.add(conductor);
		}
	}

	private void generateTrains(Vector<Train> trains) {
		int[] trainNumbers = { 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418,
				419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434 };

		for (int number : trainNumbers) {
			Train tr = new Train(number);
			trains.add(tr);
		}
	}
}
