package common;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

public class SendTestReport {

    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "2063591319@qq.com";

//    @BeforeClass
//    public void before() {
//        // 配置，一次即可
//        OhMyEmail.config(SMTP_QQ(false), "1178624121@qq.com", "tehamekpuxceieaj");
//        // 如果是企业邮箱则使用下面配置
//    }

    @AfterTest
    public void SendTestReport() throws SendMailException, FileNotFoundException {
        OhMyEmail.config(SMTP_QQ(false), "1178624121@qq.com", "tehamekpuxceieaj");

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        String mdirName = dateString + "TestReport";

        //压缩测试报告
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\");
        stringBuilder.append( mdirName );
        stringBuilder.append( ".zip" );
        String zipFileName = stringBuilder.toString();
        FileOutputStream fos1 = new FileOutputStream(new File(zipFileName));
        toZip("C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\html", fos1,true);


        File file = new File( stringBuilder.toString() );
        FileInputStream in = null;
        //如果文件不存在，我们就抛出异常或者不在继续执行
        //在实际应用中，尽量少用异常，会增加系统的负担
        if (!file.exists()){
            throw new FileNotFoundException();
        }
        try {
            in = new FileInputStream(file);
            byte bytArr[] = new byte[1024];
            int len = in.read(bytArr);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //发送邮件
        OhMyEmail.subject(mdirName)
                .from("pms-test")
                .to(TO_EMAIL)
                .html("<h1 font=red>测试报告请查收附件</h1>")
                .attach(file,mdirName+".zip")
                .send();
        Assert.assertTrue(true);
    }




    private static final int  BUFFER_SIZE = 2 * 1024;


    /**
     * 压缩成ZIP 方法1
     * @param srcDir 压缩文件夹路径
     * @param out    压缩文件输出流
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)

            throws RuntimeException{

        long start = System.currentTimeMillis();

        ZipOutputStream zos = null ;
        try {

            zos = new ZipOutputStream(out);

            File sourceFile = new File(srcDir);

            compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);

            long end = System.currentTimeMillis();

            System.out.println("压缩完成，耗时：" + (end - start) +" ms");

        } catch (Exception e) {

            throw new RuntimeException("zip error from ZipUtils",e);

        }finally{

            if(zos != null){

                try {

                    zos.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }



    }
    /**
     * 递归压缩方法
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构;
     *                          false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception{

        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }
                }
            }
        }
    }

    @Test(enabled = false)
    public void testSendText() throws SendMailException, FileNotFoundException {
        File file = new File( "C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\html.zip" );
        FileInputStream in = null;
        //如果文件不存在，我们就抛出异常或者不在继续执行
        //在实际应用中，尽量少用异常，会增加系统的负担
        if (!file.exists()){
            throw new FileNotFoundException();
        }
        try {
            in = new FileInputStream(file);
            byte bytArr[] = new byte[1024];
            int len = in.read(bytArr);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OhMyEmail.subject("这是一封测试附件邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .attach(file,"html.rar")
                .send();
        Assert.assertTrue(true);
    }

    public static void main(String[] args) throws Exception {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);

//        /** 测试压缩方法1  */
//        FileOutputStream fos1 = new FileOutputStream(new File("C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\html.zip"));
//        toZip("C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\html", fos1,true);

//        /** 测试压缩方法2  */
//        List<File> fileList = new ArrayList<>();
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
//        fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
//        FileOutputStream fos2 = new FileOutputStream(new File("c:/mytest02.zip"));
//        toZip(fileList, fos2);
    }



}
