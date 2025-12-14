package Tareas.BancoInteractivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {

    private JPanel panel1;
    private JButton INGRESARButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton REGISTRARButton;

    private int intentos = 0;
    private final int MAX_INTENTOS = 3;

    public Login() {
        // === IMPORTANTE: Establecer tamaño preferido del panel principal ===
        panel1.setPreferredSize(new Dimension(400, 400));

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (intentos >= MAX_INTENTOS) {
                    JOptionPane.showMessageDialog(null,
                            "Acceso bloqueado. Demasiados intentos fallidos.");
                    INGRESARButton.setEnabled(false);
                    return;
                }

                String usuario = textField1.getText().trim();
                String clave = new String(passwordField1.getPassword());

                if (usuario.isEmpty() || clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete ambos campos.");
                    return;
                }

                boolean acceso = false;

                try (BufferedReader br = new BufferedReader(new FileReader("src/usuarios.txt"))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] datos = linea.split(",");
                        if (datos.length == 2 &&
                                datos[0].trim().equals(usuario) &&
                                datos[1].trim().equals(clave)) {
                            acceso = true;
                            break;
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error al leer el archivo de usuarios.");
                    return;
                }

                if (acceso) {
                    intentos = 0; // Reiniciar intentos al acceder
                    JOptionPane.showMessageDialog(null, "Acceso permitido");
                    abrirOperaciones();
                    cerrarLogin();
                } else {
                    intentos++;
                    JOptionPane.showMessageDialog(null,
                            "Credenciales incorrectas. Intento "
                                    + intentos + " de " + MAX_INTENTOS);

                    if (intentos >= MAX_INTENTOS) {
                        JOptionPane.showMessageDialog(null,
                                "Acceso bloqueado por seguridad.");
                        INGRESARButton.setEnabled(false);
                    }
                }

                // Limpiar contraseña siempre
                passwordField1.setText("");
            }
        });
    }

    private void abrirOperaciones() {
        JFrame frame = new JFrame("Banco - Operaciones");
        OPERACIONES operaciones = new OPERACIONES();
        frame.setContentPane(operaciones.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cerrarLogin() {
        Window window = SwingUtilities.getWindowAncestor(panel1);
        if (window != null) {
            window.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Banco Interactivo");
            Login login = new Login();
            frame.setContentPane(login.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();                    // Respeta el preferredSize del panel
            frame.setSize(400, 400);         // Fuerza el tamaño exacto deseado
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}