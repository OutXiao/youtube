package club.wenfan.youtube.utils;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/8/16
 */
public class RandomWords {
    public String proRandomWords(int len){
        String word = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String tmp = "";
        for(int i = 0; i < len; i++){
            int rand = (int)(Math.random() * word.length());
            char c = word.charAt(rand);
            if(!tmp.contains(c+"")){
                tmp += c;
            }else{
                i--;
            }
        }
        return tmp;
    }
}
