package common;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import com.sun.mail.util.LineInputStream;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class TestUtil {

    /**
     * PDF 转 图片
     */
    public static class IcePdf {
        public static void pdf2Pic(String pdfPath, String path,int no) throws Exception {
            Document document = new Document();
            document.setFile(pdfPath);
            //缩放比例
            float scale = 2.0f;
            //旋转角度
            float rotation = 0f;

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                try {
                    String imgName = no+"-"+i + ".png";
                    System.out.println(imgName);
                    File file = new File(path + imgName);
                    ImageIO.write(rendImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        }
        public static void main(String[] args) throws Exception {

            List list = new ArrayList();
            getAllFileName( "C:\\Users\\10057\\Desktop\\pdf\\" , (ArrayList<String>) list );
            for (int i =0;i<list.size();i++){
                String filePath = String.valueOf( list.get( i ) );
                System.out.println( filePath );
                pdf2Pic(filePath, "C:\\Users\\10057\\Desktop\\合同图片\\",i);
            }

        }

        /**
         * 获取某个文件夹下的所有文件
         *
         * @param fileNameList 存放文件名称的list
         * @param path 文件夹的路径
         * @return
         */
        public static void getAllFileName(String path, ArrayList<String> fileNameList) {
//            ArrayList<String> files = new ArrayList<String>();
            boolean flag = false;
            File file = new File(path);
            File[] tempList = file.listFiles();

            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
//              System.out.println("文件：" + tempList[i]);
                    fileNameList.add(tempList[i].toString());
//                    fileNameList.add(tempList[i].getName());
                }
                if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
                    getAllFileName(tempList[i].getAbsolutePath(),fileNameList);
                }
            }
            return;
        }
    }
}
