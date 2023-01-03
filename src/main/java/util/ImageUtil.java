package util;/*
 *Author:Litao
 *Created in:
 */

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class ImageUtil {
    private String str;//验证码
    private ByteArrayInputStream image;//验证码图片

    private int width = 80;
    private int height = 46;

    public static ImageUtil getInstance(){return new ImageUtil();}

    private ImageUtil(){
        init();
    }

    private void init(){
        //初始化图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();

        g.setColor(getRandomColor(200,250));
        g.fillRect(0,0,width,height);

        Random random = new Random();
        String code = "";
        g.setFont(new Font("Times New Roman",Font.PLAIN,20));
        //随机生成数字和字母
        String str = "";
        for (int i = 0; i < 4; i++) {
            String ranChar;
            if(random.nextInt(2)%2==0){
                //再次随机生成0或1数字，若是0则随机生成大写字母，若为1，则随机生成小写字母
                if(random.nextInt(2)%2==0){
                    ranChar = String.valueOf((char)(random.nextInt(26)+65));
                    code += ranChar;
                }else{
                    ranChar = String.valueOf((char)(random.nextInt(26)+97));
                    code += ranChar;
                }

            }else{
                ranChar = String.valueOf(random.nextInt(10));
                code += ranChar;
            }
            str += ranChar;
            g.setColor(getRandomColor(20,110));
            g.drawString(ranChar,(width / 4) * i,height / 2);
        }
        this.str = str;
        g.dispose();
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image,"JPEG",imageOut);
            imageOut.close();
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = inputStream;
    }

    private Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255){fc = 255;}
        if(bc > 255){bc = 255;}
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ByteArrayInputStream getImage() {
        return image;
    }

    public void setImage(ByteArrayInputStream image) {
        this.image = image;
    }
}
