package app;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import bpogoda.spaceteam.server.command.Command;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CaptainWindow extends JFrame {

	private CaptainController captainController;

	private DefaultListModel<Command> listModel;

	private JList<Command> listCommands;

	public CaptainWindow() {

		listModel = new DefaultListModel<>();

		listCommands = new JList(listModel);

		listCommands.setCellRenderer(new ListCellRenderer<Command>() {
			@Override
			public Component getListCellRendererComponent(JList<? extends Command> list, Command value, int index,
					boolean isSelected, boolean cellHasFocus) {

				JLabel label = new JLabel(value.getCommandMessage());

				if (isSelected) {
					label.setBorder(BorderFactory.createLineBorder(Color.black));
				}

				return label;
			}
		});

		getContentPane().add(listCommands, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		JButton btnSendCommand = new JButton("Crew members, execute this command!");

		btnSendCommand.addActionListener(ev -> {
			if (listCommands.getSelectedValue() != null) {
				captainController.sendCommand(listCommands.getSelectedValue());
			}
		});

		getContentPane().add(btnSendCommand, BorderLayout.SOUTH);
	}

	public void updateCommands(List<Command> commands) {

		listModel.clear();

		commands.forEach(command -> listModel.addElement(command));

	}

	public void setCaptainController(CaptainController captainController) {
		this.captainController = captainController;
	}

}
