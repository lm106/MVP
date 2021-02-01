package imageviewer.app.swing;

import imageviewer.control.ImagePresenter;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Luzma
 * @version 8-01-2021
 * @since Práctica en Aula
 */
public class Main extends JFrame{
    public static void main(String[] args) {
        new Main().execute();
    }
    private ImagePanel imageDisplay;
    private ImagePresenter imagePresenter;
    public Main(){
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        List<Image> images = new FileImageLoader(new File("Fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.imagePresenter = new ImagePresenter(images, imageDisplay);
    }
    
     private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        return panel;
    }
    private void execute() {
        this.setVisible(true);
    }
}
