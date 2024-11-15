
public class Bicycle extends Vehicle implements Comparable<Bicycle>{
	private double weight;
	private static int[] bicyclist = {1};
	
	public Bicycle() {
		super(1, 1);
		weight = 0;
	}
	
	public Bicycle(Person driver) {
		super(driver, bicyclist);
		weight = 0;
	}
	
	public Bicycle(Person driver, double weight) {
		super(driver, bicyclist);
		if(weight < 0.0) {
			weight = 0.0;
		}
		this.weight = weight;
	}
	public void setWeight(double w) {
		this.weight = w;
	}
	@Override
	public void setDriver(Person p) throws InvalidDriverException{
		//made change
		if(p == null){
			throw new InvalidDriverException("This is a null Person object");
		}
		else if(p.getAge()>=3) {
			personsOnBoard[0][0]=p;
		}else {
		throw new InvalidDriverException("Not able to drive bycicle.");}
	}
	
	public double getWeight() {
		return weight;
	}
	@Override
	public String toString() {
		return "Bicycle [ rider= " + getDriver().getName() + " | weight= " + weight + " ]";
	}

	@Override
	public boolean equals(Object o) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Bicycle other = (Bicycle) obj;
//		return Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
		final double ACCURACY_RANGE = 0.5;
		if(o == null){
			return false;
			}
		if(this == o){
			return true;
			}
		if (o instanceof Bicycle) {
			Bicycle otherC = (Bicycle)o;
			if(this.getWeight()==otherC.getWeight()) {
				if(Math.abs(otherC.getWeight()-this.weight)<=ACCURACY_RANGE) {
						 return true;						 
				}				
			}			
		 }
		return false;
	}
	@Override
	public int compareTo(Bicycle b) {
		final double ACCURACY_RANGE = 0.5;
		if(b.getWeight()-this.getWeight() > ACCURACY_RANGE) {
            return -1;    
        }
        else if(b.getWeight()-this.getWeight() < ACCURACY_RANGE) {
            return 1;
        }
        else {
            return 0;
        }
	}

	@Override
	public boolean loadPassenger(Person p) {
		// TODO Auto-generated method stub	
		return false;
	}

	@Override
	public int loadPassengers(Person[] peeps) {
		
		return 0;
	}
}
