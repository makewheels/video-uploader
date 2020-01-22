package util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云对象存储工具类
 *
 * @time 2020-01-22 10:50
 */
public class QiniuUtil {
    private static String accessKey = "KiMZs0_pu8eTHP01Im7RY1q9I7Z3KWgXZDbzHcLl";
    private static String secretKey = "DTqDw8mg0OKLnJpurzHGdzrvfz2ljn2MUGJ5G97Z";
    private static String bucket = "video-objects";

    public static String upload(String filePath, String key) {
        Configuration cfg = new Configuration(Region.region1());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Response response = null;
        DefaultPutRet putRet = null;
        try {
            response = uploadManager.put(filePath, key, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            return "http://os.qiniu.qbserver.cn/" + putRet.key;
            return "http://q4hne1acu.bkt.clouddn.com/" + putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String url = upload("C:\\Users\\Javascripts\\EnfocusSupport.js", "awfeafewawge");
        System.out.println(url);
    }
}
