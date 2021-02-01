package imageviewer.view;

import java.util.List;
import imageviewer.model.Image;

/**
 *
 * @author Luzma
 * @version 8-01-2021
 * @since Práctica en Aula
 */
public interface ImageLoader {
    List<Image> load();
}
