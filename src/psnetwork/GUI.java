/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psnetwork;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author river
 */
public class GUI extends JFrame {

    private PSNUsers usuarios;

    private JTextField usernameField;
    private JButton addUserButton;
    private JButton deactivateUserButton;
    private JButton showInfoButton;
    private JTextField trophyUsernameField;
    private JTextField trophyGameField;
    private JTextField trophyNameField;
    private JComboBox<Trophy> trophyTypeCombo;
    private JButton addTrophyButton;

    public GUI() {
        super("PSN Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        try {
            usuarios = new PSNUsers();
            initComponents();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel userPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        userPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Usuarios"));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Usuario:"));
        usernameField = new JTextField(15);
        inputPanel.add(usernameField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addUserButton = new JButton("Añadir");
        deactivateUserButton = new JButton("Desactivar");
        showInfoButton = new JButton("Info");
        buttonPanel.add(addUserButton);
        buttonPanel.add(deactivateUserButton);
        buttonPanel.add(showInfoButton);

        userPanel.add(inputPanel);
        userPanel.add(buttonPanel);

        JPanel trophyPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        trophyPanel.setBorder(BorderFactory.createTitledBorder("Añadir Trofeo"));

        trophyPanel.add(new JLabel("Usuario:"));
        trophyUsernameField = new JTextField(10);
        trophyPanel.add(trophyUsernameField);

        trophyPanel.add(new JLabel("Juego:"));
        trophyGameField = new JTextField(10);
        trophyPanel.add(trophyGameField);

        trophyPanel.add(new JLabel("Trofeo:"));
        trophyNameField = new JTextField(10);
        trophyPanel.add(trophyNameField);

        trophyPanel.add(new JLabel("Tipo:"));
        trophyTypeCombo = new JComboBox<>(Trophy.values());
        trophyPanel.add(trophyTypeCombo);

        trophyPanel.add(new JLabel(""));
        addTrophyButton = new JButton("Añadir Trofeo");
        trophyPanel.add(addTrophyButton);

        mainPanel.add(userPanel, BorderLayout.NORTH);
        mainPanel.add(trophyPanel, BorderLayout.CENTER);

        add(mainPanel);

        addUserButton.addActionListener(e -> {
            try {
                if (!usernameField.equals("") || usernameField != null) {
                    String username = usernameField.getText().trim();
                    usuarios.addUser(username);
                    JOptionPane.showMessageDialog(this, "Usuario añadido");
                    usernameField.setText("");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        deactivateUserButton.addActionListener(e -> {
            try {
                usuarios.desactivateUser(usernameField.getText().trim());
                JOptionPane.showMessageDialog(this, "Usuario desactivado");
                usernameField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        showInfoButton.addActionListener(e -> {
            try {
                usuarios.playerInfo(usernameField.getText().trim());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        addTrophyButton.addActionListener(e -> {
            try {
                String username = trophyUsernameField.getText().trim();
                String game = trophyGameField.getText().trim();
                String trophy = trophyNameField.getText().trim();
                Trophy type = (Trophy) trophyTypeCombo.getSelectedItem();

                usuarios.addTrophieTo(username, game, trophy, type);
                JOptionPane.showMessageDialog(this, "Trofeo añadido");
                trophyUsernameField.setText("");
                trophyGameField.setText("");
                trophyNameField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
