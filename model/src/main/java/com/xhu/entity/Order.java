package com.xhu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 主键，自增。（识别订单）
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 乘客，外键(但不加约束)
     */
    private Integer userid;

    /**
     * 司机,外键(但不加约束)
     */
    private Integer driverid;

    /**
     * 出发地经度
     */
    private String callx;

    /**
     * 出发地纬度
     */
    private String cally;

    /**
     * 出发地地址
     */
    private String staddr;

    /**
     * 目的地经度
     */
    private String endx;

    /**
     * 目的地纬度
     */
    private String endy;

    /**
     * 目的地地址
     */
    private String endaddr;

    /**
     * 呼叫时间
     */
    private Date statime;

    /**
     * 接单时间
     */
    private Date replytime;

    /**
     * 下车时间
     */
    private Date endtime;

    /**
     * 费用
     */
    private BigDecimal cost;

    /**
     * 里程
     */
    private String milege;

    /**
     * 订单状态
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getDriverid() == null ? other.getDriverid() == null : this.getDriverid().equals(other.getDriverid()))
            && (this.getCallx() == null ? other.getCallx() == null : this.getCallx().equals(other.getCallx()))
            && (this.getCally() == null ? other.getCally() == null : this.getCally().equals(other.getCally()))
            && (this.getStaddr() == null ? other.getStaddr() == null : this.getStaddr().equals(other.getStaddr()))
            && (this.getEndx() == null ? other.getEndx() == null : this.getEndx().equals(other.getEndx()))
            && (this.getEndy() == null ? other.getEndy() == null : this.getEndy().equals(other.getEndy()))
            && (this.getEndaddr() == null ? other.getEndaddr() == null : this.getEndaddr().equals(other.getEndaddr()))
            && (this.getStatime() == null ? other.getStatime() == null : this.getStatime().equals(other.getStatime()))
            && (this.getReplytime() == null ? other.getReplytime() == null : this.getReplytime().equals(other.getReplytime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getMilege() == null ? other.getMilege() == null : this.getMilege().equals(other.getMilege()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getDriverid() == null) ? 0 : getDriverid().hashCode());
        result = prime * result + ((getCallx() == null) ? 0 : getCallx().hashCode());
        result = prime * result + ((getCally() == null) ? 0 : getCally().hashCode());
        result = prime * result + ((getStaddr() == null) ? 0 : getStaddr().hashCode());
        result = prime * result + ((getEndx() == null) ? 0 : getEndx().hashCode());
        result = prime * result + ((getEndy() == null) ? 0 : getEndy().hashCode());
        result = prime * result + ((getEndaddr() == null) ? 0 : getEndaddr().hashCode());
        result = prime * result + ((getStatime() == null) ? 0 : getStatime().hashCode());
        result = prime * result + ((getReplytime() == null) ? 0 : getReplytime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getMilege() == null) ? 0 : getMilege().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", driverid=").append(driverid);
        sb.append(", callx=").append(callx);
        sb.append(", cally=").append(cally);
        sb.append(", staddr=").append(staddr);
        sb.append(", endx=").append(endx);
        sb.append(", endy=").append(endy);
        sb.append(", endaddr=").append(endaddr);
        sb.append(", statime=").append(statime);
        sb.append(", replytime=").append(replytime);
        sb.append(", endtime=").append(endtime);
        sb.append(", cost=").append(cost);
        sb.append(", milege=").append(milege);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}