package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController redesController = new RedesController();
		String selectedValueString = "";

		Object[] possibleValues = { "ping", "ip" };

		while (selectedValueString != null) {
			String selectedValue = (String) JOptionPane.showInputDialog(null, "Escolha um comando (ping demora 10 segundos para rodar)", "Comando",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);

			if (selectedValue == null) {
				break;
			} else if (selectedValue.equals("ip")) {
				redesController.ip();
			} else if (selectedValue.equals("ping")) {
				redesController.ping();
			} else {

			}
		}

	}
}
