package reflection;

public class Test {
	public String name;
	private int age;
	private String hobby;

	public Test() {
		super();
	}

	public Test(String name, int age, String hobby) {
		super();
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "Test [name=" + name + ", age=" + age + ", hobby=" + hobby + "]";
	}
	
	private void hi() {
		
	}
}
