package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageDisplay.Shift;
import java.util.List;

/**
 *
 * @author Luzma
 * @version 8-01-2021
 * @since Práctica en Aula
 */
public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.on(shift());
    }
    private int current(){
        return images.indexOf(imageDisplay.currentImage());
    }
    private ImageDisplay.Shift shift(){
        return new Shift(){
            @Override
            public Image left() {
                return images.get((current() +1) % images.size());
            }

            @Override
            public Image right() {
                return images.get((current() -1 + images.size()) % images.size());
            }
            
        };
    }
}
