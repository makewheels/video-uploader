package run.qiniu;

import org.apache.commons.io.FilenameUtils;
import util.FreemakerUtil;
import util.UuidUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 七牛云，全自动跑起来，谁用七牛，就是贵啊
 *
 * @time 2020-01-22 11:23
 */
public class UploadToQiniu {
    public static void main(String[] args) throws Exception {
        String folder = "C:\\Users\\Administrator\\Videos\\Desktop\\";
        String filename = "2020.01.31GoogleEarth演示_out.mp4";
        File videoFile = new File(folder, filename);
        String videoName = FilenameUtils.getBaseName(videoFile.getName());
        String uuid = UuidUtil.getUuid();
        //上传视频
        String videoUrl = QiniuUtil.upload(videoFile.getAbsolutePath(),
                videoName + "-" + uuid + "." + FilenameUtils.getExtension(videoFile.getName())
        );
        //制作html
        Map<String, String> params = new HashMap<>();
        params.put("title", videoName);
        params.put("videoName", videoName);
        params.put("videoUrl", videoUrl);
        File htmlFile = FreemakerUtil.createHtmlByMode("video.html.ftl", "video.html", params);
        //上传html
        String htmlUrl = QiniuUtil.upload(htmlFile.getPath(), videoName.replace(" ", "-")
                + "-" + uuid + "." + FilenameUtils.getExtension(htmlFile.getName()));
        //拿到链接
        System.out.println(htmlUrl);
        //删除html文件
        htmlFile.delete();
    }
}
