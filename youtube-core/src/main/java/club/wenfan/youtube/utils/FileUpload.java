package club.wenfan.youtube.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/20
 */
public class FileUpload {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
