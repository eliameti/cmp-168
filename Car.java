
public class Car extends Vehicle implements Comparable<Car>, Announcements{
	
	private int numDoors;
	private int numWindows;

	public Car(int numDoors, int numWindows) {
		super(2,2);
		this.numDoors = numDoors;
		this.numWindows = numWindows;
	}
	
	public Car(int numDoors, int numWindows, int numSeatsPerRow) {
		super(2,numSeatsPerRow);
		this.numDoors = numDoors;
		this.numWindows = numWindows;
	}
	
	public Car(int numDoors, int numWindows, int [ ] numSeatsPerRow ) {
		super(numSeatsPerRow);
		this.numDoors = numDoors;
		this.numWindows = numWindows;
	}
	
	public  Car(int numDoors, int numWindows, Person driver, int [ ] numSeatsPerRow) {
		super(driver,numSeatsPerRow);
		this.numDoors = numDoors;
		this.numWindows = numWindows;
	}
	
	public boolean canOpenDoor(Person p) {
		int[] location = getLocationOfPersonInVehicle(p);
		int withDoor = 0;
		
		if(numDoors < (2 * numberOfRows) ) {
			withDoor = (numDoors / 2) - 1;
		}
		
		if(p.getAge() >= 5) {
			if(location[0] <= withDoor) {
				if( (location[1] == 0) || (location[1] == (personsOnBoard[location[0]].length - 1) ) ) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public boolean canOpenWindow(Person p) {
		int[] location = getLocationOfPersonInVehicle(p);
		int withWindow = 0;
		
		if(this.numDoors < (2 * numberOfRows) ) {
			withWindow = (this.numDoors / 2) - 1;
		}	
		if(p.getAge() > 2) {
			if(location[0] <= withWindow) {
				if( (location[1] == 0) || (location[1] == personsOnBoard[location[0]].length - 1) ) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public int getNumDoors() {
		return this.numDoors;
	}
	public int getNumWindows() {
		return this.numWindows;
	}
	
	@Override
	public String toString() {
		String s;
		
		s = String.format("Car: number of doors = %02d | number of windows = %02d"
				+ " | number of rows = %02d | seats per row = [", numDoors, numWindows, numberOfRows);
		
		for(int i = 0; i < numSeatsPerRow.length; i++) {
			if(i == (numSeatsPerRow.length - 1) ) {
				s += String.format("%s", numSeatsPerRow[i]);
			}
			else {
				s += String.format("%s,", numSeatsPerRow[i]);
			}
		}
		s += String.format("] | names of people on board = [");
		
		for(int i = 0; i < personsOnBoard.length; i++) {
			for(int j = 0; j < personsOnBoard[i].length; j++) {
				if(i == (personsOnBoard.length - 1) && (j == (personsOnBoard[i].length - 1)  )) {
					if(personsOnBoard[i][j] != null) {
						s += personsOnBoard[i][j].getName();
					}
				}
				else if(personsOnBoard[i][j] != null) {
					s += personsOnBoard[i][j].getName() + ",";
				}
			}
		}
		s += "]\n";
		
		return s;
	
				
	}

	@Override
	public boolean equals(Object o) {

		if(o == null){
			return false;}		
		if(this == o) {
			return true;}		
		if(o instanceof Car) {
			Car otherCar = (Car)o;			
			if(this.numDoors == otherCar.getNumDoors()) {
				if(this.numWindows == otherCar.getNumWindows()) {
					if(this.numberOfRows == otherCar.numberOfRows) {
						if(this.maxSeatsPerRow == otherCar.numberOfRows) {
							for(int i = 0; i < this.numSeatsPerRow.length; ++i) {
								if(numSeatsPerRow[i] != otherCar.numSeatsPerRow[i]) {
									return false;
								}
							}
							return true;
						}
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public String departure() {
		
		return "All Aboard\n";
	}

	@Override
	public String arrival() {
		
		return "Everyone Out\n";
	}

	@Override
	public String doNotDisturbTheDriver() {
		
		return "No Backseat Driving\n";
	}
	
	//get total seats of vehicle	
	@Override
	public int compareTo(Car c) {
		int seats=0;
		for(int i = 0;i < numberOfRows;i++) {
			seats += numSeatsPerRow[i];
		}
		int otherSeats=0;
		for(int i = 0;i < c.numberOfRows;i++) {
			otherSeats += c.numSeatsPerRow[i];
		}
		if(seats< otherSeats) {
			return -1;
		} else if(seats > otherSeats) {
			return 1;
		} else {
			return 0;
		}
        
	}

	@Override
	public boolean loadPassenger(Person p) {
		if(getNumberOfAvailableSeats() <= 0)
			return false;
		
		for(int i = 0; i < numberOfRows; i++) {
			if(i == 0 ) {
					if(p.getAge() > 5 ) {
						if(p.getHeight() > 36) {
				for(int j = 0; j < personsOnBoard[i].length; j ++) {
					if(personsOnBoard[i][j] == null) {
						personsOnBoard[i][j] = p;
						return true;
					}
				}
			}}}
			else {
				for(int j = 0; j < personsOnBoard[i].length; j ++) {
					if(personsOnBoard[i][j] == null) {
						personsOnBoard[i][j] = p;
						return true;
					}
				}
			}
			
		}
		
		return false;
	}

	@Override
	public int loadPassengers(Person[] peeps) {
		int count = 0;
		for(int i = 0; i < peeps.length; i++) {
			if(loadPassenger(peeps[i])){
				count++;
			}
		}
		return count;
		
	}


}
