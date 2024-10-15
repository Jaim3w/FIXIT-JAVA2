package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import Modelo.mdlCitas;
import Vistas.elementosTwo.TransparenteRoundedPanel;
import Vistas.elementosTwo.TransparenteRoundedPanel2;

public class citasCardsPanel extends TransparenteRoundedPanel {

    private JPanel cardsContainer; // Contenedor para las tarjetas
    private JScrollPane scrollPane; // Scroll Pane
    private int dragStartX; // Variable para guardar la posición inicial del mouse

    public citasCardsPanel() {
        setLayout(new BorderLayout()); // Usamos BorderLayout para incluir el JScrollPane
        cardsContainer = new JPanel();

        // Cambia a FlowLayout para que las tarjetas se dispongan horizontalmente
        cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Espacio entre tarjetas

        loadCards(); // Cargar las tarjetas al iniciar

        // Crear JScrollPane y agregar el panel de tarjetas
        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Barra horizontal oculta
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Barra vertical desactivada

        // Hacer que el JScrollPane y su contenedor sean transparentes
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        cardsContainer.setOpaque(false);

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);

        // Añadir el listener para detectar el arrastre
        addDragScrollingFeature();
    }

    // Método para habilitar el scrolling con el drag del mouse
    private void addDragScrollingFeature() {
        scrollPane.getViewport().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dragEndX = e.getX();
                int scrollOffset = dragStartX - dragEndX;
                JScrollBar hScrollBar = scrollPane.getHorizontalScrollBar();
                hScrollBar.setValue(hScrollBar.getValue() + scrollOffset); // Ajusta el scroll horizontal
            }
        });

        scrollPane.getViewport().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStartX = e.getX(); // Guarda la posición inicial del mouse
            }
        });
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

        // Configurar para que las barras de scroll estén invisibles
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Ocultar barra horizontal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Ocultar barra vertical

        // Hacer que el JScrollPane y su contenedor sean transparentes
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        cardsContainer.setOpaque(false);

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);
        cardsContainer.revalidate(); // Refrescar el contenedor
        cardsContainer.repaint(); // Redibujar el contenedor

        // Añadir el listener para detectar el arrastre
        addDragScrollingFeature(); // Este es el método que habilita el scroll al arrastrar

    } catch (Exception e) {
       
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
        nombreLabel.setForeground(Color.WHITE); // Texto oscuro

        JLabel fechaLabel = new JLabel("Fecha: " + cita[3]);
        fechaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fechaLabel.setForeground(Color.WHITE); // Texto oscuro

        JLabel horaLabel = new JLabel("Hora: " + cita[4]);
        horaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        horaLabel.setForeground(Color.WHITE); // Texto oscuro

        // Agregar las etiquetas al panel
        card.add(nombreLabel, BorderLayout.NORTH);
        card.add(fechaLabel, BorderLayout.CENTER);
        card.add(horaLabel, BorderLayout.SOUTH);

        return card;
    }
}
