import java.util.Date;
import java.util.List;

public class Activity {
	
	private int id, minTime, maxTime;
	private List<Resource> resources;
	private Date start, end;
	private long length;

	public Activity(int id, int minTime, int maxTime, List<Resource> resources,
			Date start, Date finish, long length) {
		super();
		this.id = id;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.resources = resources;
		this.start = start;
		this.end = finish;
		this.length = length;
	}
	

	public Date getStart() {
		return start;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public Date getEnd() {
		return end;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public int getId() {
		return id;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMinTime() {
		return minTime;
	}

	public void setMinTime(int minTime) {
		this.minTime = minTime;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}


	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", minTime=" + minTime + ", maxTime=" + maxTime + ", resources=" + resources
				+ ", start=" + start + ", end=" + end + ", length=" + length + "]";
	}

	

		

}
