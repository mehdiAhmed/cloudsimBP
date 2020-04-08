
public class Resource {
	String id, name,type, location, strategy, os;
	int dcpu;
	double memory;
	
	public Resource(String id, String name, String type, String location, String strategy, String os, int dcpu,
			double memory) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.location = location;
		this.strategy = strategy;
		this.os = os;
		this.dcpu = dcpu;
		this.memory = memory;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getDcpu() {
		return dcpu;
	}

	public void setDcpu(int dcpu) {
		this.dcpu = dcpu;
	}

	public double getMemory() {
		return memory;
	}

	public void setMemory(double memory) {
		this.memory = memory;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", type=" + type + ", location=" + location + ", strategy="
				+ strategy + ", os=" + os + ", dcpu=" + dcpu + ", memory=" + memory + "]";
	}
	
	
	

}
