package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Run {

	public static void main(String[] args) {
		
		Class test = Test.class;
		
		// 1. 클래스의 어노테이션 정보를 반환하는 getAnnotations
		System.out.println("===== 클래스의 어노테이션 정보 반환받기 =====");
		Annotation[] annotations = test.getAnnotations();
		
		System.out.println("annotations.length : " + annotations.length);
		
		for(Annotation annotation : annotations) {
			System.out.println("annotaion : " + annotation.toString());
		}
		
		System.out.println();
		
		// 2. 클래스의 생성자 정보를 반환하는 getConstructors
		System.out.println("===== 클래스의 생성자 정보 반환받기 =====");
		Constructor[] constructors = test.getConstructors();
		System.out.println("constructors.length : " + constructors.length);
		
		for(Constructor constructor : constructors) {
			System.out.println("constructor : " + constructor);
		}
		
		System.out.println();
		System.out.println("===== 리플렉션 이용하여 새로운 인스턴스 만들기 =====");
		Test test2 = null;
		try {
			Constructor constructor = test.getConstructor(String.class, Integer.TYPE, String.class);
			test2 = (Test) constructor.newInstance("이혜영", 24, "취미");
			System.out.println(test2);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		
		System.out.println();
		
		// 3. 클래스의 public 변수의 정보를 반환하는 getFields
		System.out.println("===== 클래스의 public 변수 정보 반환받기 =====");
		Field[] fields = test.getFields();
		System.out.println("fields.length : " + fields.length);
		
		for (Field field : fields) {
			System.out.println("field : " + field);
		}

		System.out.println();
		System.out.println("===== 리플렉션 이용하여 변수 정보 변경하기 =====");
		try {
			Field age = test.getDeclaredField("age");
			age.setAccessible(true);
			System.out.println(age.get(test2));
			age.set(test2, 35);
			System.out.println(age.get(test2));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		
		// 4. 클래스의 public 메소드의 정보를 반환하는 getMethods
		System.out.println("===== 클래스의 public 메소드 정보 반환받기 =====");
		Method[] methods = test.getMethods();
		System.out.println("methods.length : " + methods.length);
		
		for (Method method : methods) {
			System.out.println("method : " + method.getName()); // method의 이름만 출력됨.
		}
		
		System.out.println();
		System.out.println("===== 리플렉션 이용하여 메소드 정보 변경하기");
		try {
			Method hobby = test.getDeclaredMethod("setHobby", String.class);
			Object invokeMethod = hobby.invoke(test2, "안녕");
			System.out.println(test2);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();

		// 5. 클래스 or 변수 or 메소드의 이름을 반환하는 getName
		System.out.println("===== 이름 반환받기 =====");
		String name = test.getName();
		System.out.println("Class Name : " + name);
		
		System.out.println();

		// 6. 클래스의 패키지 정보를 반환하는 getPackage
		System.out.println("===== 패키지 정보 반환받기 =====");
		Package testPackage = test.getPackage();
		System.out.println("testPackage : " + testPackage);
		
		System.out.println();
		
		// 7. 클래스가 구현한 인터페이스의 정보를 반환하는 getInterfaces
		System.out.println("===== 인터페이스 정보 반환받기 =====");
		Class[] interfaces = test.getInterfaces();
		System.out.println("interfaces.length : " + interfaces.length);
		
		for (Class class1 : interfaces) {
			System.out.println(class1);
		}
		
		System.out.println();
		
		// 8. 클래스의 부모 클래스의 정보를 반환하는 getSuperclass
		System.out.println("===== 부모 클래스 정보 반환받기 =====");
		Class superClass = test.getSuperclass();
		System.out.println("superClass : " + superClass);
		
		System.out.println();
		
		// 9. 클래스의 모든 변수의 정보를 반환하는 getDeclearedFields
		System.out.println("===== 모든 변수(private, public 등) 정보 반환받기 =====");
		Field[] deFields = test.getDeclaredFields();
		for (Field deField : deFields) {
			System.out.println("deField : " + deField);
		}
		
		System.out.println();
		
		// 10. 클래스의 모든 메소드의 정보를 반환하는 getDeclearedMethods
		System.out.println("===== 모든 메소드(private, public 등) 정보 반환받기 =====");
		Method[] deMethods = test.getDeclaredMethods();
		for (Method deMethod : deMethods) {
			System.out.println("deMethod : " + deMethod);
			
			// 메소드의 접근제한자를 int로 반환함
			// 0 : default
			// 1 : public 
			// 2 : private 
			// 4 : protected
			System.out.println("deMethod.getModifiers() : " + deMethod.getModifiers());
			
			// 반환된 값을 통해 접근제한자가 private인지 확인함.
			if(Modifier.isPrivate(deMethod.getModifiers())) {
				// setAccessible은 해당 메소드(혹은 변수)에 접근 권한을 설정하는 메소드다.
				// 접근제한자가 private일 경우 접근할 수 있는 권한을 준다는 뜻
				deMethod.setAccessible(true);
			}
			
			// 메소드에 접근할 수 있는지, 없는지를 나타내는 메소드
			System.out.println("deMethod.isAccessible : " + deMethod.isAccessible());
		}
		
		System.out.println();
	}
}
