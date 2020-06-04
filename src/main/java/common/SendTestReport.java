package common;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

public class SendTestReport {

    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "2063591319@qq.com";

    @BeforeClass
    public void before() {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(false), "1178624121@qq.com", "tehamekpuxceieaj");
        // 如果是企业邮箱则使用下面配置
    }

    @Test
    public void testSendText() throws SendMailException, FileNotFoundException {
        File file = new File( "C:\\Users\\10057\\IdeaProjects\\pms_test\\test-output\\html.rar" );
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
            System.out.println("Message: " + new String(bytArr, 0, len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OhMyEmail.subject("这是一封测试附件邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .attach(file,"index.html")
                .send();
        Assert.assertTrue(true);
    }
}
