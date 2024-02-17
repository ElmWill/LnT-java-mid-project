package Data;

public class Employee {
	private String name;
	private String gender;
	private String rank;
	private String id;
	private double pay;
	
	public Employee(String name, String gender, String rank, String id, double pay) {
		super();
		this.setName(name);
		this.setGender(gender);
		this.setRank(rank);
		this.setId(id);
		this.setPay(pay);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
