package com.WSZW.entity;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ZwFw_CX_BizInfo implements Parcelable {
	private String trafficName;						//受理流水号
	private String objectName;					//事项名称
	private String workName;						//办事人
	private String bidDateName;				//申办日期
	private String institutionName;				//受理机构
	private String endDateName;				//办结日期
	private String departmentName;			//主管部门
	private String siteName;						//办理地点
	private List<ZwFw_CX_item_bean> list; //列表
	private int mData;
	public String getTrafficName() {
		return trafficName;
	}
	public void setTrafficName(String trafficName) {
		this.trafficName = trafficName;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getBidDateName() {
		return bidDateName;
	}
	public void setBidDateName(String bidDateName) {
		this.bidDateName = bidDateName;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getEndDateName() {
		return endDateName;
	}
	public void setEndDateName(String endDateName) {
		this.endDateName = endDateName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public List<ZwFw_CX_item_bean> getList() {
		return list;
	}
	public void setList(List<ZwFw_CX_item_bean> list) {
		this.list = list;
	}
	@Override
	public int describeContents() {
		// TODO 自动生成的方法存根
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO 自动生成的方法存根
		out.writeInt(mData);
	}
	public static final Parcelable.Creator<ZwFw_CX_BizInfo> CREATOR=new Creator<ZwFw_CX_BizInfo>() {
		
		@Override
		public ZwFw_CX_BizInfo[] newArray(int size) {
			
			return new ZwFw_CX_BizInfo[size];
		}
		
		@Override
		public ZwFw_CX_BizInfo createFromParcel(Parcel in) {
			// TODO 自动生成的方法存根
			return new ZwFw_CX_BizInfo();
		}
	};

}
