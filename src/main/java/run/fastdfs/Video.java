package run.fastdfs;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @time 2020-02-01 21:03
 */
@Data
@Document
public class Video {
    @Id
    private String _id;
    private String title;
    private String videoId;
    private String htmlUrl;
    private String m3u8Url;
    private Date createTime;
}
