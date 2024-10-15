package Vistas;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import Modelo.mdlCarros;
import Vistas.elementosTwo.TransparenteRoundedPanel2; // Usamos este para las tarjetas
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class carrosCardsPanel extends JPanel {

    private JPanel cardsContainer; // Contenedor para las tarjetas
    private JScrollPane scrollPane; // Scroll Pane
    private int dragStartX; // Variable para guardar la posición inicial del mouse
    private Image backgroundImage; // Imagen de fondo para el panel principal

    public carrosCardsPanel() {
        setLayout(new BorderLayout());
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Espacio entre tarjetas
        cardsContainer.setOpaque(false); // Hacer el contenedor de tarjetas transparente

        loadCards(); // Cargar las tarjetas al iniciar

        // Crear JScrollPane y agregar el panel de tarjetas
        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Barra horizontal oculta
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Barra vertical desactivada

        // Hacer que el JScrollPane y su contenedor sean transparentes
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);

        // Añadir el listener para detectar el arrastre
        addDragScrollingFeature();
    }

    private void loadCards() {
        cardsContainer.removeAll(); // Limpiar el contenedor antes de cargar nuevos carros

        try {
            mdlCarros carroModel = new mdlCarros();
            ArrayList<String[]> carrosData = carroModel.obtenerCarrosCards(); // Obtener los carros del modelo

            // Verifica si se obtuvieron carros
            if (carrosData == null || carrosData.isEmpty()) {
                System.out.println("No se encontraron carros.");
                return; // Salir si no hay carros
            }

            // Crear las tarjetas y añadirlas al contenedor
            for (String[] carro : carrosData) {
                cardsContainer.add(createCarroCard(carro));
            }

            // Asegúrate de que el contenedor se actualice
            cardsContainer.revalidate(); // Refrescar el contenedor
            cardsContainer.repaint(); // Redibujar el contenedor

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los carros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Crear una card para cada carro utilizando el componente TransparenteRoundedPanel2
    private JPanel createCarroCard(String[] carro) {
        TransparenteRoundedPanel2 card = new TransparenteRoundedPanel2(); // Usar el panel con fondo
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 250)); // Ajustar tamaño según sea necesario
        card.setOpaque(false); // Hacer la tarjeta transparente

        // Crear un panel para las etiquetas de texto
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // Disposición vertical
        textPanel.setOpaque(false); // Hacer el panel de texto transparente

        // Crear etiquetas para los datos del carro
        JLabel marcaLabel = new JLabel("Carro de : " + carro[1]);
        marcaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        marcaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        marcaLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen
        marcaLabel.setForeground(Color.WHITE); // Texto en blanco

        JLabel modeloLabel = new JLabel("Modelo: " + carro[2]);
        modeloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modeloLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        modeloLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen
        modeloLabel.setForeground(Color.WHITE); // Texto en blanco

        JLabel añoLabel = new JLabel("Color: " + carro[3]);
        añoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        añoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        añoLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen
        añoLabel.setForeground(Color.WHITE); // Texto en blanco

        JLabel colorLabel = new JLabel("Placas: " + carro[0]);
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        colorLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen
        colorLabel.setForeground(Color.WHITE); // Texto en blanco

        // Cargar y escalar la imagen
        JLabel imageLabel = new JLabel();
        try {
            String imagenUrl = carro[7];
            ImageIcon imageIcon = new ImageIcon(new URL(imagenUrl)); // Cargar la imagen desde la URL
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Escalar la imagen
            imageIcon = new ImageIcon(image);
            imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setForeground(Color.WHITE); // Texto en blanco en caso de error
        }

        // Agregar la imagen y las etiquetas al panel de texto
        textPanel.add(imageLabel); // Imagen en la parte superior
        textPanel.add(marcaLabel);
        textPanel.add(modeloLabel);
        textPanel.add(añoLabel);
        textPanel.add(colorLabel);

        // Agregar el panel de texto a la tarjeta
        card.add(textPanel, BorderLayout.CENTER); // Texto en el centro

        return card;
    }

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo si está cargada
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Asegúrate de que el panel sea transparente
    @Override
    public boolean isOpaque() {
        return false;
    }
}