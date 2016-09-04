package club.iwalker.activemq.release.producer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangchen on 2016/9/4.
 */
public class ProductInfo implements Serializable {
    private int id; //商品ID
    private String name; //商品名称
    private BigDecimal price; //商品价格
    private Date createTime;

    public ProductInfo() {
    }

    public ProductInfo(int id, String name, BigDecimal price, Date createTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
