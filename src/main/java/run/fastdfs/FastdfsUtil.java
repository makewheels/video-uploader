package run.fastdfs;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.IOException;

/**
 * @time 2020-02-07 22:06
 */
public class FastdfsUtil {
    private static StorageClient1 storageClient1;

    static {
        try {
            ClientGlobal.initByProperties("fastdfs.properties");
            //创建客户端
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker Server
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取一个storage server
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //创建一个storage存储客户端
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static String upload(File file) throws IOException, MyException {
        return storageClient1.upload_file1(file.getAbsolutePath(),
                FilenameUtils.getExtension(file.getName()), null);
    }

    public static void main(String[] args) throws IOException, MyException {
        File file = new File("C:\\Users\\Administrator\\Downloads\\2.txt");
        String id = FastdfsUtil.upload(file);
        System.out.println(id);
    }
}
