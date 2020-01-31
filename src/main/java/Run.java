import org.apache.commons.io.FilenameUtils;
import util.FreemakerUtil;
import util.QiniuUtil;
import util.UuidUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 全自动跑起来
 *
 * @time 2020-01-22 11:23
 */
public class Run {
    public static void main(String[] args) throws Exception {
//        String folder = "C:\\Users\\Administrator\\Videos\\Desktop\\";
        String folder = "C:\\Users\\Administrator\\Videos\\Desktop";
        String filename = "2020.01.31GoogleEarth演示.mp4";
        File videoFile = new File(folder, filename);
        String videoName = FilenameUtils.getBaseName(videoFile.getName());
        String uuid = UuidUtil.getUuid();
        //视频转码
        File newVideoFile = new File(folder, videoName + "_out.mp4");
        Process process = Runtime.getRuntime().exec("ffmpeg.exe -i " + videoFile.getAbsolutePath()
                + " -r 30 " + videoName + "_out.mp4");
        InputStream fis = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
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
        String htmlUrl = QiniuUtil.upload(htmlFile.getPath(),
                videoName.replace(" ", "-")
                        + "-" + uuid + "." + FilenameUtils.getExtension(htmlFile.getName()));
        //拿到链接
        System.out.println(htmlUrl);
        //删除html文件
        htmlFile.delete();
    }
}
