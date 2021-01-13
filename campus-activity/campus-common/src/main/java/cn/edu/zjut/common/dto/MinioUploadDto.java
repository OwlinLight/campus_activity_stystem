package cn.edu.zjut.common.dto;

/**
 * 文件上传返回结果
 * Created by iris on 2020/12/23.
 * DTO（ Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象。
 */
public class MinioUploadDto {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
