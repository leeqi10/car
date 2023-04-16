package com.xhu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName driver
 */
@TableName(value ="driver")
@Data
public class Driver implements Serializable {
    /**
     * 主键，自增。
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 账号(唯一)
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 司机名字
     */
    private String name;

    /**
     * 电话号码(唯一)
     */
    private String tel;

    /**
     * 身份证（唯一）
     */
    private String idno;

    /**
     * 性别
     */
    private String sex;

    /**
     * 驾驶证号
     */
    private String jsz;

    /**
     * 车牌号
     */
    private String carno;

    /**
     * 车型
     */
    private String catype;

    /**
     * 司机收入
     */
    private String money;

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
        Driver other = (Driver) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getJsz() == null ? other.getJsz() == null : this.getJsz().equals(other.getJsz()))
            && (this.getCarno() == null ? other.getCarno() == null : this.getCarno().equals(other.getCarno()))
            && (this.getCatype() == null ? other.getCatype() == null : this.getCatype().equals(other.getCatype()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getJsz() == null) ? 0 : getJsz().hashCode());
        result = prime * result + ((getCarno() == null) ? 0 : getCarno().hashCode());
        result = prime * result + ((getCatype() == null) ? 0 : getCatype().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", tel=").append(tel);
        sb.append(", idno=").append(idno);
        sb.append(", sex=").append(sex);
        sb.append(", jsz=").append(jsz);
        sb.append(", carno=").append(carno);
        sb.append(", catype=").append(catype);
        sb.append(", money=").append(money);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}