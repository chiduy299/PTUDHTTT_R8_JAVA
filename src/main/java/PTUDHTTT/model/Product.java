package PTUDHTTT.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
	@Id
	private String id;
	private String product_name;
	private float rating;
	private double unit_price;
	private String unit_product_name;
	private int product_remaining;
	private int sell_number;
	private int created_at;
	private int updated_at;
	private boolean is_available;
	private String id_category;
	private String store_id;
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	private List<String> images;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public String getUnit_product_name() {
		return unit_product_name;
	}
	public void setUnit_product_name(String unit_product_name) {
		this.unit_product_name = unit_product_name;
	}
	public int getProduct_remaining() {
		return product_remaining;
	}
	public void setProduct_remaining(int product_remaining) {
		this.product_remaining = product_remaining;
	}
	public int getSell_number() {
		return sell_number;
	}
	public void setSell_number(int sell_number) {
		this.sell_number = sell_number;
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
	public boolean isIs_available() {
		return is_available;
	}
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
	public String getId_category() {
		return id_category;
	}
	public void setId_category(String id_category) {
		this.id_category = id_category;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}

