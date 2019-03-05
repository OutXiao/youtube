package club.wenfan.youtube.utils;

import club.wenfan.youtube.properties.CosProperties;
import club.wenfan.youtube.properties.SecurityProperties;
import com.mysql.jdbc.log.LogFactory;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.AnonymousCOSCredentials;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/20
 */

@Component
public class UploadFileUtil {

    @Autowired
    private SecurityProperties securityProperties;

    private static COSCredentials cred = null;
    private static TransferManager transferManager = null;
    private static COSClient cosClient = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");

    private Logger log = LoggerFactory.getLogger(getClass());

    public URL uploadFile(File file,String type){

        CosProperties cos = securityProperties.getCos();
        String appid = cos.getAppid();
        String secretId = cos.getSecretId();
        String SecretKey = cos.getSecretKey();
        String bucket = cos.getBucket();
        String area = cos.getArea();

        cred = new BasicCOSCredentials(secretId, SecretKey);
        // 2 设置bucket的区域,
        ClientConfig clientConfig = new ClientConfig(new Region(area));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
        // 指定要上传到 COS 上的路径
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        // 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
        transferManager = new TransferManager(cosClient, threadPool);

        String bucketName = bucket+"-"+appid;
        //System.out.println(cosProperties.getBucket());
        return upload(transferManager,bucketName,file,type);
    }

    //路径上传
    private URL upload(TransferManager transferManager, String bucket, File file, String type){
        System.out.println("上传时间：" + sdf.format(new Date()));
        RandomWords randomWords=new RandomWords();
        String proRandomWord=randomWords.proRandomWords(32);//随机生成len位字符串
        String dir="/otherFile/";

        switch (type.toLowerCase()) {
            case "jpg":
            case "gif":
            case "png":
            case "jpeg":
                dir = "/image/";
                break;
            case "mp4":
                dir = "/video/";
                break;
        }
        String key = dir+proRandomWord+"."+type;
        new Thread(() -> {
            UploadResult uploadResult =null;
            try {

                PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file);
                // 本地文件上传
                Upload upload = transferManager.upload(putObjectRequest);
                // 异步（如果想等待传输结束，则调用 waitForUploadResult）
                uploadResult = upload.waitForUploadResult();
                log.info(uploadResult.getKey());
                log.info("上传结束时间:" + sdf.format(new Date()));
                log.info("上传成功");

            }catch (Throwable tb){
                log.error("上传失败");
                tb.printStackTrace();
            }finally {
                // 关闭 TransferManger
                transferManager.shutdownNow();
            }

        }).start();
        //获取上传成功之后文件的下载地址
        // 生成匿名的请求签名，需要重新初始化一个匿名的 cosclient
        // 1 初始化用户身份信息, 匿名身份不用传入ak sk
        COSCredentials cred = new AnonymousCOSCredentials();
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(securityProperties.getCos().getArea()));
        // 3 生成 cos 客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, key, HttpMethodName.GET);
        URL url = cosclient.generatePresignedUrl(req);
        log.info(url.toString());
        return url;
    }

}

