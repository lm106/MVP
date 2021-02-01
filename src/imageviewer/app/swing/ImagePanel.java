package imageviewer.app.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Luzma
 * @version 8-01-2021
 * @since Práctica en Aula
 */
public class ImagePanel extends JPanel implements ImageDisplay{
    private Image image;
    private BufferedImage bitmap, bitmap2;
    private int offset;
    private Shift shift;
    public  ImagePanel(){
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    @Override 
    public void paint (Graphics g){
        if(bitmap != null) g.drawImage(bitmap, offset, 0, null);
        if(bitmap2 != null & offset != 0) g.drawImage(bitmap2, offset < 0? bitmap.getWidth() + offset : offset-bitmap2.getWidth(), 0, null);
        
    }
    @Override
    public void display(Image image) {
        this.image = image;
        this.bitmap = load(image);
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }
    
    private static BufferedImage load(Image image) {
        try {
            return ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            System.out.println("ERROR: (file not found" + ex.getMessage());
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{

        private int initial;

        public MouseHandler() {
            
        }

        @Override
        public void mouseClicked(MouseEvent me) { }

        @Override
        public void mousePressed(MouseEvent me) {
            this.initial = me.getX();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            int shift = shift(me.getX());
            if(Math.abs(shift) > getWidth()/2){
                image = imageAt(shift(me.getX()));
                bitmap = load(image);
            }
            offset = 0; 
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent me) { }

        @Override
        public void mouseExited(MouseEvent me) { }

        @Override
        public void mouseDragged(MouseEvent me) {
            int shift = shift(me.getX());
            if(shift == 0) bitmap2 = null;
            else if (offset/shift <= 0) bitmap2 = load(imageAt(shift));           
            offset = 0;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent me) { }

        private int shift(int x) {
            return x - initial;
        }

        
    }
    private Image imageAt(int shift) {
        if(shift>0) return this.shift.left();
        if(shift<0) return this.shift.right();
        return null;
        
    }
    
}
