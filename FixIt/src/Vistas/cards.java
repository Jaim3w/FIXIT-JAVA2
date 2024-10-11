/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Modelo.mdlCitas;

/**
 *
 * @author Jaimew
 */
public class cards extends JPanel {
    public cards() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para incluir el JScrollPane
        
        mdlCitas citaModel = new mdlCitas();
        ArrayList<String[]> citasData = citaModel.obtenerCitasCards(); // Obtener las citas del modelo

        // Crear un panel para contener las tarjetas
        JPanel cardsContainer = new JPanel();
        cardsContainer.setLayout(new GridLayout(0, 1, 5, 5)); // Grid con una sola columna y espacio entre las tarjetas
        
        // Crear las tarjetas y añadirlas al contenedor
        for (String[] cita : citasData) {
            cardsContainer.add(createCitaCard(cita));
        }

        // Crear JScrollPane y agregar el panel de tarjetas
        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);
    }

    // Crear una card para cada cita
    private JPanel createCitaCard(String[] cita) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Borde para diferenciar cada tarjeta
        card.setPreferredSize(new Dimension(300, 100)); // Ajustar tamaño según sea necesario

        // Crear etiquetas para los datos de la cita
        JLabel fechaLabel = new JLabel("Fecha: " + cita[3]);
        fechaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel horaLabel = new JLabel("Hora: " + cita[4]);
        horaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel descripcionLabel = new JLabel("Descripción: " + cita[5]);
        descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descripcionLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Agregar las etiquetas al panel
        card.add(fechaLabel, BorderLayout.NORTH);
        card.add(horaLabel, BorderLayout.CENTER);
        card.add(descripcionLabel, BorderLayout.SOUTH);

        return card;
    }
}

