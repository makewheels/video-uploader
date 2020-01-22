import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import util.FreemakerUtil;
import util.QiniuUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全自动跑起来
 *
 * @time 2020-01-22 11:23
 */
public class Run {
    public static void main(String[] args) throws Exception {
        File videoFile = new File("");
        //视频转码
        //上传视频
//        String videoUrl = QiniuUtil.upload(videoFile.getAbsolutePath(), videoFile.getName());
        //制作html
        Map<String, String> params = new HashMap<>();
        params.put("ccc", "fwwfea");
        File htmlByMode = FreemakerUtil.createHtmlByMode("video.ftl", "test.html", params);
        System.out.println(htmlByMode);
        //上传html
        //拿到链接
    }
}
