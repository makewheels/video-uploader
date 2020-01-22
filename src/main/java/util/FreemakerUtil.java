package util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * @time 2020-01-22 12:21
 */
public class FreemakerUtil {
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);

    static {
        try {
            configuration.setDirectoryForTemplateLoading(
                    new File(FreemakerUtil.class.getResource("/freemaker/").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createHtmlByMode(String modeName, String targetFileName, Map<String, String> params) throws Exception {
        String folder = System.getProperty("java.io.tmpdir");
        File outFile = new File(folder + File.separator + targetFileName);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
        try {
            Template template = configuration.getTemplate(modeName);
            Writer writer = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
            template.process(params, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outFile;
    }
}
