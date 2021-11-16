package image;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class ImageRead {

    private BufferedImage image;

    private InputStream InputStream;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public InputStream getInputStream() {
        return InputStream;
    }

    public void setInputStream(InputStream inputStream) {
        InputStream = inputStream;
    }
}
