package tma.com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer_group")
public class CustomerGroup implements Serializable{

	private static final long serialVersionUID = -6048356432523366364L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "customerGroup", fetch = FetchType.EAGER)
	private Set<Customer> customers = new HashSet<Customer>();
	
	public CustomerGroup() {}
	
	public CustomerGroup(String name) {
		this.name = name;
	}
	
	public CustomerGroup(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public CustomerGroup(int id, String name, Set<Customer> customers) {
		super();
		this.id = id;
		this.name = name;
		this.customers = customers;
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
	
	/*public Set<Customer> getCustomers() {
		return customers;
	}*/

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
}
