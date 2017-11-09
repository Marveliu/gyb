package cn.wizzer.modules.models.gy;

import cn.wizzer.common.base.Model;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
/**
* @author Wizzer.cn
* @time   2017-11-09 16:40:06
*/
@Table("gy_inf")
public class Gy_inf extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private String gyid;
	@Column
	private String userid;
	@Column
	private String realname;
	@Column
	private String qq;
	@Column
	private String email;
	@Column
	private String birthday;
	@Column
	private String phone;
	@Column
	private String idcard;
	@Column
	private Integer sex;
	@Column
	private String college;
	@Column
	private String school;
	@Column
	private String major;
	@Column
	private String regyear;
	@Column
	private Integer stulevel;
	@Column
	private Integer status;
	@Column
	private Integer opby;
	@Column
	private Integer opat;
	@Column
	private Integer delflag;
		public String getGyid()
	{
		return gyid;
	}
	public void setGyid(String gyid)
	{
		this.gyid=gyid;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}
	public String getRealname()
	{
		return realname;
	}
	public void setRealname(String realname)
	{
		this.realname=realname;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq=qq;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday=birthday;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public String getIdcard()
	{
		return idcard;
	}
	public void setIdcard(String idcard)
	{
		this.idcard=idcard;
	}
	public Integer getSex()
	{
		return sex;
	}
	public void setSex(Integer sex)
	{
		this.sex=sex;
	}
	public String getCollege()
	{
		return college;
	}
	public void setCollege(String college)
	{
		this.college=college;
	}
	public String getSchool()
	{
		return school;
	}
	public void setSchool(String school)
	{
		this.school=school;
	}
	public String getMajor()
	{
		return major;
	}
	public void setMajor(String major)
	{
		this.major=major;
	}
	public String getRegyear()
	{
		return regyear;
	}
	public void setRegyear(String regyear)
	{
		this.regyear=regyear;
	}
	public Integer getStulevel()
	{
		return stulevel;
	}
	public void setStulevel(Integer stulevel)
	{
		this.stulevel=stulevel;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status=status;
	}
	public Integer getOpby()
	{
		return opby;
	}
	public void setOpby(Integer opby)
	{
		this.opby=opby;
	}
	public Integer getOpat()
	{
		return opat;
	}
	public void setOpat(Integer opat)
	{
		this.opat=opat;
	}
	public Integer getDelflag()
	{
		return delflag;
	}
	public void setDelflag(Integer delflag)
	{
		this.delflag=delflag;
	}

}