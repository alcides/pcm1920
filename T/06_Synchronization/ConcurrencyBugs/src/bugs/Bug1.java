package bugs;
public class Bug1 {
	
	public int a = 0;
	
	
	// To evidence the bug, remove the synchronized keyword below
	public synchronized void up() {
		// synchronized(this) {
		a++;
		//}
	}

	// To evidence the bug, remove the synchronized keyword below
	public synchronized void down() {
		a--;
	}
	
	public static void main(String[] args) {
		Bug1 b = new Bug1();
		Bug1 b2 = new Bug1();
		
		
		System.out.println(b);
		

		/*
		 		
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = sb;
		sb.append("hello");
		sb.append(" "); 
		 
		// t1
		synchronized (sb) {
			
			// something
			System.out.println(1);
			// wait for 1 second
			System.out.println(2);
		}
		
		// T2
		synchronized (sb2) {
			// something
			System.out.println(3);
			// wait for 1 second
			System.out.println(4);
		}
		
		// T3
		synchronized (sb2) {
			// something
			System.out.println(5);
			// wait for 1 second
			System.out.println(6);
		}
		
		System.out.println(sb.toString());
		*/
		
		
		
		
		Thread t1 = new Thread() {
			public void run() {
				
				for (int i=0; i < 5000; i++) {
					b.up();
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				
				for (int i=0; i < 5000; i++) {
					b2.down();
				}
			}
		};
		
		
		t2.start();
		t1.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(b.a);
	}
	
}
