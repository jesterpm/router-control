import java.util.Enumeration;

import gnu.io.CommPortIdentifier;

public class MyClass {
	public static void main(String... args) {
		Enumeration ports = CommPortIdentifier.getPortIdentifiers();
	
        while (ports.hasMoreElements()) {
           CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
           
           System.out.println(port.getName());
        } 
	}

}