package tma.com.dto;

public class CustomerDTO {
	
	private int id;
	private String name;
	private String address;
	private int groupId;
	
	public CustomerDTO() {}

	public CustomerDTO(int id, String name, String address, int groupId) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.groupId = groupId;
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
