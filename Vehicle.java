
public abstract class Vehicle {
	protected Person [][] personsOnBoard;
	protected int numberOfRows;
	protected int maxSeatsPerRow;
	protected int[] numSeatsPerRow;
	
	public Vehicle(int numRows, int numSeatsPerRow) {
		if(numRows > 0) {
			numberOfRows = numRows;
		}
		
		if(numSeatsPerRow > 0) {
			maxSeatsPerRow = numSeatsPerRow;
		}
		
		personsOnBoard = new Person[numRows][maxSeatsPerRow];
		
		this.numSeatsPerRow = new int[numRows];
		for(int i = 0;i < this.numSeatsPerRow.length; i++) {
			this.numSeatsPerRow[i] = maxSeatsPerRow;
		}
		
	}
	
	public Vehicle(int[] numSeatsPerRow) {
		
		this.numSeatsPerRow = numSeatsPerRow;
		
		numberOfRows = this.numSeatsPerRow.length;
		
		maxSeatsPerRow = 0;
		for(int i = 0; i < this.numSeatsPerRow.length; i++) {
			if(this.numSeatsPerRow[i] > maxSeatsPerRow) {
				maxSeatsPerRow = this.numSeatsPerRow[i];
			}
		}
		
		personsOnBoard = new Person[numberOfRows][];
		for(int i = 0; i < personsOnBoard.length; i++) {
			personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
		}
		
	}
	
	public Vehicle(Person driver, int[] numSeatsPerRow) {
		this.numSeatsPerRow = numSeatsPerRow;
		
		numberOfRows = this.numSeatsPerRow.length;
		
		maxSeatsPerRow = 0;
		for(int i = 0; i < this.numSeatsPerRow.length; i++) {
			if(this.numSeatsPerRow[i] > maxSeatsPerRow) {
				maxSeatsPerRow = this.numSeatsPerRow[i];
			}
		}
		
		personsOnBoard = new Person[numberOfRows][];
		for(int i = 0; i < personsOnBoard.length; i++) {
			personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
		}
		
		if(driver.hasDriverLicense() == true) {
			personsOnBoard[0][0] = driver;
		}
		
	}

	public abstract boolean loadPassenger(Person p);
	
	public abstract int loadPassengers(Person[] peeps);
	
	public void setDriver(Person p) throws InvalidDriverException{
		if(p == null){
			throw new InvalidDriverException("This is a null Person object");
		}
		else if(p.hasDriverLicense() == false) {
			throw new InvalidDriverException("This person does not have a drivers license");
		}
		else {
			personsOnBoard[0][0]=p;
		}
	}
	
	public Person getDriver(){
		if(personsOnBoard[0][0] != null) {
			Person driver = personsOnBoard[0][0];
			return driver;
		}
		return null;
	}

	public boolean hasDriver() {
		if(personsOnBoard[0][0] == null) {
			return false;
		}
		else if( (personsOnBoard[0][0] != null) && (personsOnBoard[0][0].hasDriverLicense() == false) ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getNumberOfAvailableSeats() {
		//maybe fixed now
		int availableSeats = 0;
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numSeatsPerRow[i]; ++j) {
				if (personsOnBoard[i][j] == null) {
					availableSeats++;
				}
			}
		}
		return availableSeats;
	}
    
	public int getNumberOfAvailableSeatsInRow(int row) {
		//may need fixing
		int usedSeats = 0;
		int availableSeats = 0;
		
		
		if( (row < 0) || (row > (personsOnBoard.length - 1) ) ) {
			return -1;
		}
		else {
			availableSeats = personsOnBoard[row].length;
			for(int i = 0; i < personsOnBoard[row].length; ++i) {
				if(personsOnBoard[row][i] != null) {
					usedSeats++;
				}
			}
			return availableSeats - usedSeats;
		}
	}
	
	public int getNumberOfPeopleOnBoard() {		
		 int onBoard = 0;
		 
		 for(int i = 0; i < personsOnBoard.length; ++i) {
			 for(int j = 0; j < personsOnBoard[i].length; ++j) {
				 if(personsOnBoard[i][j] != null) 
					 onBoard++;
			 }
		 }
		 return onBoard ;
	}
	 
	public int getNumberOfPeopleInRow(int row) {
		int usedSeats = 0;
		
		if( (row < 0) || (row > (personsOnBoard.length - 1) ) ) {
			return -1;
		}
		else {
			for(int i = 0; i < personsOnBoard[row].length; i++) {
				if(personsOnBoard[row][i] != null) {
					usedSeats++;
				}
			}
			return usedSeats;
		}
	}
	
	public Person getPersonInSeat(int row, int col) {
		if( (row >= 0) && (row < personsOnBoard.length) ) {
			if ( (col >= 0) && (col < personsOnBoard[row].length) ) {
				if(personsOnBoard[row][col] != null) {
					return personsOnBoard[row][col];
				}
			}
		}
		return null;
	}
	
	public int[] getLocationOfPersonInVehicle(Person p) {
		int[] location = {-1,-1};
		
		for(int i = 0;i < personsOnBoard.length; i++) {
			for(int j = 0; j < personsOnBoard[i].length; j++){
				if(personsOnBoard[i][j] instanceof Person) {
					if( personsOnBoard[i][j].equals(p) ) {
						location[0] = i;
						location[1] = j;
					}
				}
			}
		}
		return location;
	}
	
	public Person[] getPeopleInRow(int row){
		Person[] inRow;
		int inRowIndex = 0;
		
		if( !(row >= 0 && row < personsOnBoard.length) ) {
			return null;
		}
		
		int count = 0;
		for(int i = 0; i < personsOnBoard[row].length; i++) {
			if(personsOnBoard[row][i] instanceof Person) {
				count++;
			}
		}
		
		if(count == 0) {
			return null;
		}
		else {
			inRow = new Person[count];
			for(int i = 0;i < personsOnBoard[row].length; i++) {
				if(inRowIndex < inRow.length) {
					if(personsOnBoard[row][i] instanceof Person) {
						try {
							inRow[inRowIndex] = personsOnBoard[row][i].clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						inRowIndex++;
					}
				}
			}
			return inRow;
		}
	}
	
	public Person[][] getPeopleOnBoard(){
		Person[][] copyOfBoard;

		copyOfBoard = new Person[numberOfRows][];
		for(int i = 0; i < personsOnBoard.length; i++) {
			copyOfBoard[i] = new Person[this.numSeatsPerRow[i]];
		}
		
		for(int row = 0;row < personsOnBoard.length; row++) {
			for(int col = 0; col < personsOnBoard[row].length; col++){
				if(personsOnBoard[row][col] instanceof Person) {
					try {
						copyOfBoard[row][col] = personsOnBoard[row][col].clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return copyOfBoard;
	}
	
	public boolean isPersonInVehicle(Person p){
		
		for(int row = 0;row < personsOnBoard.length; row++) {
			for(int col = 0; col < personsOnBoard[row].length; col++){
				if(personsOnBoard[row][col] != null) {
					if( personsOnBoard[row][col].equals(p) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
		
	public boolean isPersonDriver(Person p) {
		boolean isDriver = false;
		
		if(getDriver() instanceof Person) {
			if(getDriver().equals(p)) {
				return true;
			}
		}
		return isDriver;
	}	

	public int getNumberOfRows() {
		// TODO Auto-generated method stub
		return numberOfRows;
	}

}
