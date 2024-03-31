package admin_user.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	private String tutor;
	private String price;
	public Course(int id, String name, String tutor, String price) {
		super();
		this.id = id;
		this.name = name;
		this.tutor = tutor;
		this.price = price;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
