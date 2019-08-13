package tma.com.dto;

public class CustomerGroupDTO {

	private int id;
	private String name;
	
	public CustomerGroupDTO() {}

	public CustomerGroupDTO(String name) {
		this.name = name;
	}
	
	public CustomerGroupDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}
