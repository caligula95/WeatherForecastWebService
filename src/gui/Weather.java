package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import xmlparse.CityParser;
import xmlparse.WeatherParser;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Weather {
	CityParser xml = new CityParser();;
	WeatherParser weatherParser;
	JComboBox comboBox;
	private JFrame frame;
	private JTextField textLocation;
	private JTextField textTime;
	private JTextField textWind;
	private JTextField textVisibility;
	private JTextField textSky;
	private JTextField textTemperature;
	private JTextField textHumidity;
	private JTextField textPressure;
	private JTextField txtUkraine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Weather window = new Weather();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Weather() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 457, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(246, 316, 181, 22);
		panel.add(comboBox);
		
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton btnNewButton = new JButton("Show cities");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				
				ArrayList<String> list = xml.getCiites(txtUkraine.getText());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (String ls:list) {
					comboBox.addItem(ls);
				}
			}
		});
		btnNewButton.setBounds(291, 351, 136, 25);
		panel.add(btnNewButton);
		
		JLabel lblTemperature = new JLabel("Location");
		lblTemperature.setBounds(12, 40, 113, 16);
		panel.add(lblTemperature);
		
		textLocation = new JTextField();
		textLocation.setBounds(110, 37, 317, 22);
		panel.add(textLocation);
		textLocation.setColumns(10);
		
		JLabel Time = new JLabel("Time");
		Time.setBounds(12, 72, 113, 16);
		panel.add(Time);
		
		textTime = new JTextField();
		textTime.setColumns(10);
		textTime.setBounds(110, 69, 317, 22);
		panel.add(textTime);
		
		JLabel lblWind = new JLabel("Wind");
		lblWind.setBounds(12, 104, 113, 16);
		panel.add(lblWind);
		
		textWind = new JTextField();
		textWind.setColumns(10);
		textWind.setBounds(110, 101, 317, 22);
		panel.add(textWind);
		
		textVisibility = new JTextField();
		textVisibility.setColumns(10);
		textVisibility.setBounds(110, 133, 317, 22);
		panel.add(textVisibility);
		
		JLabel lblVisibility = new JLabel("Visibility");
		lblVisibility.setBounds(12, 136, 113, 16);
		panel.add(lblVisibility);
		
		textSky = new JTextField();
		textSky.setColumns(10);
		textSky.setBounds(110, 164, 317, 22);
		panel.add(textSky);
		
		JLabel lblSky = new JLabel("Sky");
		lblSky.setBounds(12, 167, 113, 16);
		panel.add(lblSky);
		
		textTemperature = new JTextField();
		textTemperature.setColumns(10);
		textTemperature.setBounds(110, 199, 317, 22);
		panel.add(textTemperature);
		
		JLabel label_2 = new JLabel("Temperature");
		label_2.setBounds(12, 202, 113, 16);
		panel.add(label_2);
		
		textHumidity = new JTextField();
		textHumidity.setColumns(10);
		textHumidity.setBounds(110, 234, 317, 22);
		panel.add(textHumidity);
		
		JLabel lblHumidity = new JLabel("Humidity");
		lblHumidity.setBounds(12, 237, 113, 16);
		panel.add(lblHumidity);
		
		textPressure = new JTextField();
		textPressure.setColumns(10);
		textPressure.setBounds(110, 269, 317, 22);
		panel.add(textPressure);
		
		JLabel lblPressure = new JLabel("Pressure");
		lblPressure.setBounds(12, 272, 113, 16);
		panel.add(lblPressure);
		
		JLabel lblSelectCountry = new JLabel("Enter country ");
		lblSelectCountry.setBounds(12, 319, 136, 16);
		panel.add(lblSelectCountry);
		
		txtUkraine = new JTextField();
		txtUkraine.setText("Ukraine");
		txtUkraine.setBounds(110, 316, 124, 22);
		panel.add(txtUkraine);
		txtUkraine.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Show weather");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weatherParser = new WeatherParser();
				// TODO Auto-generated method stub
				try {
					weatherParser.readXml((String)comboBox.getSelectedItem(), txtUkraine.getText());
					textHumidity.setText(weatherParser.getHumidity());
					textLocation.setText(weatherParser.getLocation());
					textPressure.setText(weatherParser.getPressure());
					textSky.setText(weatherParser.getSkyCondition());
					textTime.setText(weatherParser.getTime());
					textVisibility.setText(weatherParser.getVisibility());
					textWind.setText(weatherParser.getWind());
					textTemperature.setText(weatherParser.getTemperature());
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(110, 351, 124, 25);
		panel.add(btnNewButton_1);
	}
}
