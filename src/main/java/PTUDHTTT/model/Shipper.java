package PTUDHTTT.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Shippers")
public class Shipper {
	@Id
	private String id;
	private String name;
	private int created_at;
	private int updated_at;
	private String avatar_url;
	private String cmnd;
	private String address;
	private String phone;
	private String password;
	private int sex;
	private boolean is_enabled;
	private int number_order_delivered;
	private String area_type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public int getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public boolean isIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	public int getNumber_order_delivered() {
		return number_order_delivered;
	}
	public void setNumber_order_delivered(int number_order_delivered) {
		this.number_order_delivered = number_order_delivered;
	}
	public String getArea_type() {
		return area_type;
	}
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}
}
