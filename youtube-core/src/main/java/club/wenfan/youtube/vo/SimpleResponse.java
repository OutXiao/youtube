package club.wenfan.youtube.vo;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/7 14:18
 */
public class SimpleResponse {

    public Object message;

    public SimpleResponse(Object message) {
        this.message = message;
    }
    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
