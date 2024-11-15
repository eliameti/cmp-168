import java.util.Objects;

public class Person implements Cloneable{
	private String name;
	private boolean hasDriverLicense;
	private int age;//years
	private int height; //inches
	
	public Person(String name, boolean hasDriverLicense, int age, int height) {
		this.name = name;
		this.hasDriverLicense = hasDriverLicense;
		this.age = age;
		this.height = height;
		
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasDriverLicense() {
		return hasDriverLicense;
	}
	
	public int getAge() {
			return age;
	}
	
	public int getHeight() {
			return height;
	}
	
	@Override
	public Person clone()throws CloneNotSupportedException{  
		return (Person)super.clone();  
		}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Person other = (Person) o;
		return age == other.age && hasDriverLicense == other.hasDriverLicense && height == other.height
				&& Objects.equals(name, other.name);
	}
	
	@Override
	public String toString(){
		String hasDL;
		if(hasDriverLicense == true) {
			hasDL = "has license";
		}else {
			hasDL = "no license";
		}
		return String.format("Person [name= %10s | age= %02d | height= %02d | %s]", name, age, height, hasDL);
	}
	
	
}
