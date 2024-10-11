package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Modelo.mdlCitas;

public class citasCardsPanel extends JPanel {

    private JPanel cardsContainer; // Contenedor para las tarjetas

    public citasCardsPanel() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para incluir el JScrollPane
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new GridLayout(0, 1, 5, 5)); // Grid con una sola columna y espacio entre las tarjetas

        loadCards(); // Cargar las tarjetas al iniciar
    }

    public void loadCards() {
         cardsContainer.removeAll(); // Limpiar el contenedor antes de cargar nuevas citas

    try {
        mdlCitas citaModel = new mdlCitas();
        ArrayList<String[]> citasData = citaModel.obtenerCitasCards(); // Obtener las citas del modelo
        
        // Verifica si se obtuvieron citas
        if (citasData == null || citasData.isEmpty()) {
            System.out.println("No se encontraron citas.");
            return; // Salir si no hay citas
        }

        // Crear las tarjetas y añadirlas al contenedor
        for (String[] cita : citasData) {
            cardsContainer.add(createCitaCard(cita));
        }

        // Crear JScrollPane y agregar el panel de tarjetas
        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);
        cardsContainer.revalidate(); // Asegúrate de refrescar el contenedor
        cardsContainer.repaint(); // Redibuja el contenedor
    } catch (Exception e) {
        e.printStackTrace(); // Muestra la excepción completa en la consola
        JOptionPane.showMessageDialog(this, "Error al cargar las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
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
