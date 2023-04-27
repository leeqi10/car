package com.xhu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName passenger
 */
@TableName(value ="passenger")
@Data
public class Passenger implements Serializable {
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
     * 用户名字
     */
    private String name;

    /**
     * 电话号码(唯一)
     */
    private String tel;

    /**
     * 身份证（唯一）
     */
    private Integer idno;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户余额
     */
    private Double money;

    /**
     * 经度
     */
    private String longitudefrom;

    /**
     * 维度
     */
    private String latitudefrom;

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
        Passenger other = (Passenger) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getLongitudefrom() == null ? other.getLongitudefrom() == null : this.getLongitudefrom().equals(other.getLongitudefrom()))
            && (this.getLatitudefrom() == null ? other.getLatitudefrom() == null : this.getLatitudefrom().equals(other.getLatitudefrom()));
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
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getLongitudefrom() == null) ? 0 : getLongitudefrom().hashCode());
        result = prime * result + ((getLatitudefrom() == null) ? 0 : getLatitudefrom().hashCode());
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
        sb.append(", money=").append(money);
        sb.append(", longitudefrom=").append(longitudefrom);
        sb.append(", latitudefrom=").append(latitudefrom);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}