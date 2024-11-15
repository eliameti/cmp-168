
public class Bus extends Car{
	
	public Bus(int[] numSeatsPerRow) {
		super(2,((2 * numSeatsPerRow.length) -1), numSeatsPerRow);
	}
	
	public Bus(Person driver, int[] numSeatsPerRow) {
		super(2,((2 * numSeatsPerRow.length) -1),driver, numSeatsPerRow);
		
	}
	
	public boolean canOpenDoor(Person p) {
		
		if( p == null) {
			return false;
		}
		if(this.isPersonDriver(p) || (p.getAge()>5 && p.getHeight()>40)){
	          return true;
	      }
	       return false;
	}
	
	public boolean canOpenWindow(Person p) {
		
		if(p == null) {
			return false;
			}
//		}
//		if(p.getAge()>5)
//			return super.canOpenWindow(p);
//			
//		return false;
			if ((p.getAge()>5)&&super.canOpenWindow(p)) {
				
				return true;
			}
			return false;

	}
	
	@Override
	public String toString() {
		return "Bus is an extension of " + super.toString();	
	}
	
	@Override
	public String departure() {
		return super.departure() + "The Bus\n";
	}

	@Override
	public String arrival() {
		return super.arrival() + "Of The Bus\n";
	}

	@Override
	public String doNotDisturbTheDriver() {
		return super.doNotDisturbTheDriver() + "On The Bus\n";
	}
	
	@Override
	public boolean loadPassenger(Person p) {
		return super.loadPassenger(p);
	}

	@Override
	public int loadPassengers(Person[] peeps) {
		return super.loadPassengers(peeps);
	}

}
