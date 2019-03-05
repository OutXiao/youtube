package club.wenfan.youtube.rest;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/3/1 10:37
 */
public class RestResult {

    public static RestMsg success(){
        return RestMsg.SUCCESS;
    }

    public static RestMsg failure(){
        return RestMsg.FAILURE;
    }

    public static RestMsg badRequest(){
        return RestMsg.BAD_REQUEST;
    }


}


