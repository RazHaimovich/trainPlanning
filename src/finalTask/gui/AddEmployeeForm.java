package finalTask.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

import finalTask.Storge;
import finalTask.entities.Driver;
import finalTask.entities.Employee;
import finalTask.entities.StationManager;
import finalTask.entities.TrainConductor;
import finalTask.enums.JobType;
import finalTask.utils.FileUtil;
import finalTask.utils.StorgeUtils;

public class AddEmployeeForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final String fileName = "./defaultSelect.txt";
	private Storge storge;
	private JComboBox<String> typeComboBox;
	private JTextField idField;
	private JTextField nameField;
	private JButton addButton;
	private JLabel errorLabel;
	boolean isInitializing = true;

	public AddEmployeeForm(JFrame owner, Storge storge) {
		super(owner, "Add Employee", true);
		this.storge = storge;

		List<String> jobTypeNames = Arrays.stream(JobType.values()).map(JobType::getDisplayName)
				.collect(Collectors.toList());

		typeComboBox = new JComboBox<>(jobTypeNames.toArray(new String[0]));
		idField = new JTextField();
		nameField = new JTextField();
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				requestFocus();
			}
		});

		initDialog();
	}

	private void initDialog() {
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();

		GridBagLayout layout = new GridBagLayout();
		this.add(mainPanel);
		mainPanel.setLayout(layout);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		typeComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isInitializing)
					return;
				if (e.getActionCommand() == "comboBoxChanged") {
					FileUtil.writeToFile(fileName, typeComboBox.getSelectedItem().toString());
				}
			}

		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.6667;
		gbc.fill = GridBagConstraints.BOTH;

		mainPanel.add(panel, gbc);
		panel.setLayout(new GridLayout(3, 2));

		JLabel typeLabel = new JLabel("Type:");
		panel.add(typeLabel);

		panel.add(typeComboBox);

		JLabel idLabel = new JLabel("ID:");
		panel.add(idLabel);

		panel.add(idField);

		JLabel nameLabel = new JLabel("Name:");
		panel.add(nameLabel);

		panel.add(nameField);

		addButton = new JButton("Add Employee");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
					errorLabel.setText("ID and Name fields cannot be empty.");
					return;
				}

				Employee entity = null;
				JobType jobType = JobType.fromDisplayName(typeComboBox.getSelectedItem().toString());
				switch (jobType) {
				case StationManager:
					entity = new StationManager(idField.getText(), nameField.getText());
					break;
				case Driver:
					entity = new Driver(idField.getText(), nameField.getText());
					break;
				case TrainConductor:
					entity = new TrainConductor(idField.getText(), nameField.getText());
					break;
				}
				if (entity != null) {
					StorgeUtils.addEmployee(storge, entity);
					close();
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.3333;
		gbc.insets = new Insets(10, 0, 0, 0);
		mainPanel.add(addButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(5, 5, 5, 5);
		mainPanel.add(errorLabel, gbc);

		reInitDialog();
	}

	public void reInitDialog() {
		typeComboBox.setSelectedIndex(0);
		idField.setText("");
		nameField.setText("");
		isInitializing = false;
		String defaultJobSelected = FileUtil.readFromFile(fileName);
		System.out.println(typeComboBox.getItemCount());
		if (defaultJobSelected != null)
			typeComboBox.setSelectedItem(defaultJobSelected);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		this.setVisible(true);
		this.toFront();
		requestFocus();
	}

	public void close() {
		this.dispose();
	}

	public void requestFocus() {
		this.setAlwaysOnTop(true);
		this.requestFocusInWindow();
	}

}
