package cn.wizzer.modules.models.gy;

import cn.wizzer.common.base.Model;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Name;
/**
* @author Wizzer.cn
* @time   2017-11-09 16:40:06
*/
@Table("gy_auth")
public class Gy_auth extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	@Name
	@Prev(els = {@EL("uuid()")})
	private String id;
	@Column
	private String gyid;
	@Column
	private String time;
	@Column
	private String idcardf;
	@Column
	private String idcardb;
	@Column
	private String stucard;
	@Column
	private String opby;
	@Column
	private String opat;
	@Column
	private Integer status;
		public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id=id;
	}
	public String getGyid()
	{
		return gyid;
	}
	public void setGyid(String gyid)
	{
		this.gyid=gyid;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time=time;
	}
	public String getIdcardf()
	{
		return idcardf;
	}
	public void setIdcardf(String idcardf)
	{
		this.idcardf=idcardf;
	}
	public String getIdcardb()
	{
		return idcardb;
	}
	public void setIdcardb(String idcardb)
	{
		this.idcardb=idcardb;
	}
	public String getStucard()
	{
		return stucard;
	}
	public void setStucard(String stucard)
	{
		this.stucard=stucard;
	}
	public String getOpby()
	{
		return opby;
	}
	public void setOpby(String opby)
	{
		this.opby=opby;
	}
	public String getOpat()
	{
		return opat;
	}
	public void setOpat(String opat)
	{
		this.opat=opat;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status=status;
	}

}