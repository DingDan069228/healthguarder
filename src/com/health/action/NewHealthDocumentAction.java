package com.health.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.postgresql.util.Base64;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

import com.health.entity.BloodGlucose;
import com.health.entity.ClinicReport;
import com.health.entity.HospitalReport;
import com.health.entity.PhysicalReport;
import com.health.entity.Weight;
import com.health.service.IBloodGlucoseService;
import com.health.service.IClinicReportService;
import com.health.service.IHospitalReportService;
import com.health.service.IPhysicalReportService;
import com.health.service.IUserService;
import com.health.service.IWeightService;
import com.health.util.FileUtil;
import com.opensymphony.xwork2.ActionSupport;
@Component("uploadHealthDocument_action")
public class NewHealthDocumentAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private IBloodGlucoseService bloodGlucoseService;
	private IWeightService weightService;
	private IClinicReportService clinicReportService;
	private IHospitalReportService hospitalReportService;
	private IPhysicalReportService physicalReportService;
	private IUserService userService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService = userService;
	}
	@Resource(name="bloodGlucose_service")
	public void setBloodGlucoseService(IBloodGlucoseService bloodGlucoseService){
		this.bloodGlucoseService = bloodGlucoseService;
	}
	@Resource(name="weight_service")
	public void setWeightService(IWeightService weightService){
		this.weightService = weightService;
	}
	@Resource(name="clinicReport_service")
	public void setClinicReportService(IClinicReportService clinicReportService){
		this.clinicReportService = clinicReportService;
	}
	@Resource(name="physicalReport_service")
	public void setPhysicalReportService(IPhysicalReportService physicalReportService){
		this.physicalReportService = physicalReportService;
	}
	@Resource(name="hospitalReport_service")
	public void setHospitalReportService(IHospitalReportService hospitalReportService){
		this.hospitalReportService = hospitalReportService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void uploadHealthDocument() throws Exception{
		Integer responseValue = -1;
		int index = Integer.valueOf(request.getParameter("index"));
		String user = request.getParameter("user");
		//获取用户对应的id号
		int userId = userService.getIdByUserName(user);
		ArrayList<String> pictureNames = new ArrayList<String>();
		BASE64Decoder decoder = new BASE64Decoder();
		System.out.println(index+"");
		if(index==0){
			//MySQL中的decimal数据类型decimal(4，2)中的4表示总的位数，包括小数点后的位数
			float value = Float.valueOf(request.getParameter("value"));
			int period = Integer.valueOf(request.getParameter("period"));
			Timestamp time = Timestamp.valueOf(request.getParameter("time")+":00");
			String remark = request.getParameter("remark");
			System.out.print(value+"---"+period+"---"+time+"---"+remark);
			BloodGlucose bloodGlucose = new BloodGlucose(period, value,time, remark, userId);
			System.out.println(bloodGlucose);
			responseValue = bloodGlucoseService.save(bloodGlucose);
			System.out.println(responseValue);
		}else if(index==1){
			int value = Integer.valueOf(request.getParameter("value"));
			Timestamp time = Timestamp.valueOf(request.getParameter("time")+":00");
			String remark = request.getParameter("remark");
			System.out.print(value+"---"+time+"---"+remark);
			Weight weight = new Weight(value, time, remark, userId);
			responseValue = weightService.save(weight);
		}else if(index==2){
			String hospital = request.getParameter("hospital");
			String time = request.getParameter("time");
			String remark = request.getParameter("remark");
			int number = Integer.valueOf(request.getParameter("pictureNumber"));
			for(int i=0;i<number;i++){
				String picName = UUID.randomUUID().toString().replace("-", "");
				String photo = request.getParameter("photo"+i);
				//byte[] buffer = decoder.decodeBuffer(photo);
				byte[] buffer = Base64.decode(photo);
				System.out.println("图片的长度"+buffer.length);
				for(int j=0;j<buffer.length;j++){
					if(buffer[j]<0){
						//调整异常数据
						buffer[j]+=256;
					}
				}
				String returnPath = FileUtil.savePicture(picName, buffer, FileUtil.CLINIC);
				if(returnPath==null){
					responseValue = -1;
					System.out.println("图片保存失败");
					return;
				}
				pictureNames.add(picName+".png");
			}
			String url = buildUrl(pictureNames);
			ClinicReport instance = new ClinicReport(hospital, time, remark, url, userId);
			responseValue = clinicReportService.save(instance);
		}else if(index==3){
			String hospital = request.getParameter("hospital");
			String time = request.getParameter("time");
			String remark = request.getParameter("remark");
			int number = Integer.valueOf(request.getParameter("pictureNumber"));
			for(int i=0;i<number;i++){
				String picName = UUID.randomUUID().toString().replace("-", "");
				String photo = request.getParameter("photo"+i);
				byte[] buffer = decoder.decodeBuffer(photo);
				System.out.println("图片的长度"+buffer.length);
				for(int j=0;j<buffer.length;j++){
					if(buffer[j]<0){
						//调整异常数据
						buffer[j]+=256;
					}
				}
				String returnPath = FileUtil.savePicture(picName, buffer, FileUtil.HOSPITAL);
				if(returnPath==null){
					System.out.println("图片保存失败");
					responseValue = -1;
					return ;
				}
				pictureNames.add(picName+".png");
			}
			String url = buildUrl(pictureNames);
			HospitalReport instance = new HospitalReport(hospital, time, remark, url, userId);
			responseValue = hospitalReportService.save(instance);
		}else if(index==4){
			String hospital = request.getParameter("hospital");
			String time = request.getParameter("time");
			String remark = request.getParameter("remark");
			int number = Integer.valueOf(request.getParameter("pictureNumber"));
			for(int i=0;i<number;i++){
				String picName = UUID.randomUUID().toString().replace("-", "");
				String photo = request.getParameter("photo"+i);
				byte[] buffer = decoder.decodeBuffer(photo);
				System.out.println("图片的长度"+buffer.length);
				for(int j=0;j<buffer.length;j++){
					if(buffer[j]<0){
						//调整异常数据
						buffer[j]+=256;
					}
				}
				String returnPath = FileUtil.savePicture(picName, buffer, FileUtil.PHYSICAL);
				if(returnPath==null){
					System.out.println("图片保存失败");
					responseValue = -1;
					return;
				}
				pictureNames.add(picName+".png");
			}
			String url = buildUrl(pictureNames);
			PhysicalReport instance = new PhysicalReport(hospital, time, remark, url, userId);
			responseValue = physicalReportService.save(instance);
		}
		//当response的值为-1的时候抛出异常，这样客户端便会上传失败，
		//客户端返回的异常为 org.apache.http.client.HttpResponseException：Internal Server Error
		if(responseValue==-1){
			throw new Exception("插入数据失败");
		}
	}
	//url图片之间用;间隔
	private String buildUrl(ArrayList<String> list){
		StringBuffer sb = new StringBuffer();
		if(list.size()==0)
			return null;
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				sb.append(list.get(i));
			}else{
				sb.append(list.get(i)+";");
			}
		}
		return sb.toString();
	}
}
