package image;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/4/15
 */
public class SlideVerificationCodeDemo {
    public static void main(String[] args) throws IOException {
        SlideVerificationCodeDemo demo = new SlideVerificationCodeDemo();
        ImageRead origin = demo.readImage("images/img.png");
        ImageRead template = demo.readImage("images/template.png");
        ImageVerificationVo imageVerificationVo = ImageVerificationUtil.generateCutoutCoordinates(origin.getImage(), template.getImage());
        demo.cutImage(origin, template);
    }

    /**
     * 图片生成图像矩阵
     * @param bufferedImage  图片源
     * @return 图片矩阵
     */
    private static int[][] getMatrix(BufferedImage bufferedImage) {
        int[][] matrix = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                matrix[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return matrix;
    }

    private ImageRead cutImage(ImageRead origin, ImageRead template) throws IOException {

        int templateImageWidth = template.getImage().getWidth();
        int templateImageHeight = template.getImage().getHeight();

        //  切块图   根据模板图尺寸创建一张透明图片
        BufferedImage cutoutImage = new BufferedImage(templateImageWidth, templateImageHeight, template.getImage().getType());
        printImage(cutoutImage, "cutImage.png");
        //  获取模板图片矩阵
        int[][] templateImageMatrix = getMatrix(template.getImage());
        return null;
    }


    private ImageRead readImage(String sourcePath) throws IOException {
        ImageRead imageRead = new ImageRead();
        imageRead.setImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(sourcePath)));
        imageRead.setInputStream(this.getClass().getClassLoader().getResourceAsStream(sourcePath));
        return imageRead;
    }

    private void printImage(BufferedImage bufferedImage, String fileName) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        printImage(input, fileName);
    }

    private void printImage(InputStream is, String fileName) throws IOException {
        File file = new File("/Users/jimmy/Work/Jimmy/javaDemo/src/main/resources/images/" + fileName);
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            fileImageOutputStream.write(b);
        }
        fileImageOutputStream.close();
        is.close();
    }

    private void printImage(ImageRead imageRead, String fileName) throws IOException {
        printImage(imageRead.getInputStream(), fileName);
    }
}
