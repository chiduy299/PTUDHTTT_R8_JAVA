package PTUDHTTT.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Stores")
public class Store {
	@Id
	private String id;
	private String name;
	private int created_at;
	private int updated_at;
	private String cmnd;
	private String email;
	private String district;
	private String ward;
	private String street;
	private String city;
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
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public boolean isIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	public int getTotal_orders() {
		return total_orders;
	}
	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}
	public String getArea_type() {
		return area_type;
	}
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}
	private String phone;
	private String password;
	private boolean is_enabled;
	private int total_orders;
	private String area_type;
}
