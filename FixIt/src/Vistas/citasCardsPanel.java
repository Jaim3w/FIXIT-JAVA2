package Vistas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Modelo.mdlCitas;
import Vistas.elementosTwo.TransparenteRoundedPanel2;

public class citasCardsPanel extends JPanel {

    private JPanel cardsContainer; // Contenedor para las tarjetas

    public citasCardsPanel() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para incluir el JScrollPane
        cardsContainer = new JPanel();
        
        // Cambia a FlowLayout para que las tarjetas se dispongan horizontalmente
        cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Espacio entre tarjetas

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
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Desactiva la barra vertical

        // Hacer que el JScrollPane y su contenedor sean transparentes
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        cardsContainer.setOpaque(false); // Asegúrate de que el contenedor sea transparente

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);
        cardsContainer.revalidate(); // Asegúrate de refrescar el contenedor
        cardsContainer.repaint(); // Redibuja el contenedor
        
scrollPane.setOpaque(false);
scrollPane.getViewport().setOpaque(false);
scrollPane.setBorder(BorderFactory.createEmptyBorder());
cardsContainer.setOpaque(false);

    } catch (Exception e) {
        e.printStackTrace(); // Muestra la excepción completa en la consola
        JOptionPane.showMessageDialog(this, "Error al cargar las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    // Crear una card para cada cita
    private JPanel createCitaCard(String[] cita) {
        TransparenteRoundedPanel2 card = new TransparenteRoundedPanel2();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 100)); // Ajustar tamaño según sea necesario

        // Crear etiquetas para los datos de la cita
        JLabel nombreLabel = new JLabel("Cliente: " + cita[1]);
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel fechaLabel = new JLabel("Fecha: " + cita[3]);
        fechaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel horaLabel = new JLabel("Hora: " + cita[4]);
        horaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Agregar las etiquetas al panel
        card.add(nombreLabel, BorderLayout.NORTH);
        card.add(fechaLabel, BorderLayout.CENTER);
        card.add(horaLabel, BorderLayout.SOUTH);
        
nombreLabel.setOpaque(false); // Hace que la etiqueta sea transparente
fechaLabel.setOpaque(false); // Hace que la etiqueta sea transparente
horaLabel.setOpaque(false); // Hace que la etiqueta sea transparente
        

        return card;
    }
}
