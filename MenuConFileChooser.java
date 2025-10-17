/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.javaaplicacion1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuConFileChooser extends javax.swing.JFrame {

    private JTextArea areaTexto;
    private JScrollPane scrollPane;

    public MenuConFileChooser() {
        initComponents();
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        areaTexto = new JTextArea();
        areaTexto.setEditable(true); // Ahora puedes editarlo si quieres
        scrollPane = new JScrollPane(areaTexto);

        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(scrollPane, BorderLayout.CENTER);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        menuAbrir = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visor y Guardador de Archivos de Texto");

        jMenuArchivo.setText("Archivo");

        menuAbrir.setText("Abrir...");
        menuAbrir.addActionListener(evt -> menuAbrirActionPerformed(evt));
        jMenuArchivo.add(menuAbrir);

        menuGuardar.setText("Guardar como...");
        menuGuardar.addActionListener(evt -> menuGuardarActionPerformed(evt));
        jMenuArchivo.add(menuGuardar);

        menuSalir.setText("Salir");
        menuSalir.addActionListener(evt -> System.exit(0));
        jMenuArchivo.add(menuSalir);

        jMenuBar1.add(jMenuArchivo);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null); // Centra la ventana
    }

    // Método para abrir archivos
    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir archivo de texto");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                areaTexto.setText(""); // Limpia el área antes de cargar
                String linea;
                while ((linea = lector.readLine()) != null) {
                    areaTexto.append(linea + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo:\n" + ex.getMessage());
            }
        }
    }

    // Método para guardar archivos
    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo de texto");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int resultado = fileChooser.showSaveDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            // Añade la extensión .txt si el usuario no la puso
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
                escritor.write(areaTexto.getText());
                JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo:\n" + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MenuConFileChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MenuConFileChooser().setVisible(true));
    }

    // Variables
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JPanel jPanel1;
}