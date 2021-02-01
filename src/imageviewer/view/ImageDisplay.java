package imageviewer.view;

import imageviewer.model.Image;


/**
 *
 * @author Luzma
 * @version 8-01-2021
 * @since Práctica en Aula
 */
public interface ImageDisplay {
    public void display(Image image);

    public Image currentImage();
    void on (Shift shift);
    interface Shift{
        Image left();
        Image right();
        
    }
}
