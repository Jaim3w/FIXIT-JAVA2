package Vistas;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import Modelo.mdlCarros; // Asegúrate de importar el modelo correcto
import Vistas.elementosTwo.TransparenteRoundedPanel;

public class carrosCardsPanel extends JPanel {

    private JPanel cardsContainer; // Contenedor para las tarjetas

    public carrosCardsPanel() {
        setLayout(new BorderLayout());
        cardsContainer = new JPanel();
        cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Espacio entre tarjetas

        loadCards(); // Cargar las tarjetas al iniciar
    }

    public void loadCards() {
    cardsContainer.removeAll(); // Limpiar el contenedor antes de cargar nuevos carros

    try {
        mdlCarros carroModel = new mdlCarros(); // Asegúrate de que este es el modelo correcto
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

        // Crear JScrollPane y agregar el panel de tarjetas
        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // **Establecer el fondo blanco para el JScrollPane**
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE); // Asegurar que el viewport sea blanco también

        // Establecer el fondo del contenedor de tarjetas al color deseado (por ejemplo, gris claro)
        cardsContainer.setBackground(new Color(240, 240, 240)); // Cambia esto al color que desees

        // Agregar el JScrollPane al panel principal
        add(scrollPane, BorderLayout.CENTER);
        cardsContainer.revalidate();
        cardsContainer.repaint();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los carros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    // Crear una card para cada carro
    private JPanel createCarroCard(String[] carro) {
        TransparenteRoundedPanel card = new TransparenteRoundedPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(200, 250)); // Ajustar tamaño según sea necesario

        // Crear un panel para las etiquetas de texto
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // Disposición vertical

        // Crear etiquetas para los datos del carro
        JLabel marcaLabel = new JLabel("Carro de : " + carro[1]);
        marcaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        marcaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        marcaLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen

        JLabel modeloLabel = new JLabel("Modelo: " + carro[2]);
        modeloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modeloLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        modeloLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen

        JLabel añoLabel = new JLabel("Color: " + carro[3]);
        añoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        añoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        añoLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen

        JLabel colorLabel = new JLabel("Placas: " + carro[0]);
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        colorLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Margen

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
            // Puedes mostrar una imagen por defecto o un mensaje de error aquí
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
}
