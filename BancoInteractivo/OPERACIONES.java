package Tareas.BancoInteractivo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OPERACIONES {

    protected JPanel panel1;
    private JButton DEPOSITOButton;
    private JButton RETIROButton;
    private JButton TRANSFERENCIAButton;
    private JButton SALIRButton;

    private double saldo = 1000.00;

    public OPERACIONES() {

        JOptionPane.showMessageDialog(panel1,
                "¡Bienvenido!\n\nSaldo inicial: $1000.00",
                "Banco", JOptionPane.INFORMATION_MESSAGE);

        DEPOSITOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog(panel1,
                        "Monto a depositar:\nSaldo actual: $" + String.format("%.2f", saldo));

                if (input != null && !input.trim().isEmpty()) {
                    try {
                        double monto = Double.parseDouble(input);

                        if (monto > 0) {
                            saldo += monto;
                            JOptionPane.showMessageDialog(panel1,
                                    "Depósito exitoso!\n+$" + String.format("%.2f", monto)
                                            + "\n\nNuevo saldo: $" + String.format("%.2f", saldo),
                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(panel1,
                                    "El monto debe ser mayor a 0",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1,
                                "Ingrese solo números",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        RETIROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog(panel1,
                        "Monto a retirar:\nSaldo actual: $" + String.format("%.2f", saldo));

                if (input != null && !input.trim().isEmpty()) {
                    try {
                        double monto = Double.parseDouble(input);

                        if (monto <= 0) {
                            JOptionPane.showMessageDialog(panel1,
                                    "Monto inválido",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (monto > saldo) {
                            JOptionPane.showMessageDialog(panel1,
                                    "Saldo insuficiente",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            saldo -= monto;
                            JOptionPane.showMessageDialog(panel1,
                                    "Retiro exitoso!\n-$" + String.format("%.2f", monto)
                                            + "\n\nNuevo saldo: $" + String.format("%.2f", saldo),
                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1,
                                "Ingrese solo números",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        TRANSFERENCIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panel = new JPanel();
                JTextField destinatario = new JTextField(15);
                JTextField montoField = new JTextField(10);

                panel.add(new JLabel("Destinatario:"));
                panel.add(destinatario);
                panel.add(Box.createHorizontalStrut(15));
                panel.add(new JLabel("Monto:"));
                panel.add(montoField);

                int opcion = JOptionPane.showConfirmDialog(
                        panel1, panel, "Transferencia", JOptionPane.OK_CANCEL_OPTION);

                if (opcion == JOptionPane.OK_OPTION) {

                    String nombre = destinatario.getText().trim();
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(panel1,
                                "Falta el nombre del destinatario",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        double monto = Double.parseDouble(montoField.getText());

                        if (monto <= 0) {
                            JOptionPane.showMessageDialog(panel1,
                                    "Monto inválido",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (monto > saldo) {
                            JOptionPane.showMessageDialog(panel1,
                                    "Saldo insuficiente",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            saldo -= monto;
                            JOptionPane.showMessageDialog(panel1,
                                    "Transferencia exitosa a " + nombre
                                            + "\n-$" + String.format("%.2f", monto)
                                            + "\n\nNuevo saldo: $" + String.format("%.2f", saldo),
                                    "Transferencia", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1,
                                "Monto inválido",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (JOptionPane.showConfirmDialog(
                        panel1,
                        "¿Desea salir del sistema?",
                        "Salir",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
