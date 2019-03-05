package club.wenfan.youtube;

import club.wenfan.youtube.bean.Video;

import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2019/2/23 10:20
 */
public class VideoVO {


    private int recordsTotal;

    private int recordsFiltered;

    private List<Video> data;

    public VideoVO(int recordsTotal, int recordsFiltered, List<Video> data) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }


    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Video> getData() {
        return data;
    }

    public void setData(List<Video> data) {
        this.data = data;
    }
}
