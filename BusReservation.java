package busReservationSystem2;
import java.util.*;
class User{
	UserVerification verify;
	public User(UserVerification verify) {
		this.verify=verify;
	}

	int login(List<String[]> login) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter UserName");
		String username=sc.nextLine();
		System.out.println("Enter Password");
		String password=sc.nextLine();
//		boolean check=false;
		int index=-1;

		for(String []s:login) {
		if(s[0].equals(username) && s[4].equals(password)) {
//			check= true;
			index=login.indexOf(s); 
		    }
	    }
		return index;	
	}
	
	void logout() {
		System.out.println("Thanking of choosing out service");
		System.out.println("Press 1 for remember password||Press 2 for exit");
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		switch(option) {
			case(1):
				System.out.println("remember password");
			    break;
			case(2):
				System.out.println("Does not remember password");
			    break;
		}
	}
	String[] forgetPassword(List<String[]> customerlogin) {
		System.out.println("Enter user name");
		Scanner sc=new Scanner(System.in);
		String username=sc.nextLine();
		String[]currUser = null;
		String newPassword=null;
		int flag=0;
		boolean verification = false;
		for(String[]s:customerlogin) {
			if(username.equals(s[0])) {
				currUser=s;
				flag=1;
			}
		}
		if(flag==0) {
			System.out.println("Invalid user name");
			forgetPassword(customerlogin);
		}
		else {
		verification=verify.phnoVerifing(currUser);
		}
		if(verification) {
			System.out.println("Enter new password");
			newPassword=sc.nextLine();
			currUser[4]=newPassword;
		}
		else {
			System.out.println("Your phone number is wrong");
			forgetPassword(customerlogin);
		}
		return currUser;
	}
	
}
class UserVerification{
	boolean phnoVerifing(String[] currUser) {
		System.out.println("Enter phone number");
		Scanner sc=new Scanner(System.in);
		String phoneNo=sc.nextLine();
		if(phoneNo.equals(currUser[3])){
			return true;
		}
		else {
		return false;
		}
	}
	boolean emailVerifing() {
		System.out.println("Enter Mailid");
		Scanner sc=new Scanner(System.in);
		String mail=sc.nextLine();
		return false;
		
	}
}

class Passenger {
	private String name;
	private String  age;
	private String address;
	private String phno;
	private String id;
	private String password;
	Reservation booking;
	UserVerification verify;
	Passenger(UserVerification verify){
		System.out.println("wellcome to ABC Travellers");
		this.verify=verify;
	}
	public Passenger(String[] customerInfo,Reservation booking) {
		this.name=customerInfo[0];
		this.age=customerInfo[1];
		this.address=customerInfo[2];
		this.phno=customerInfo[3];
		this.password=customerInfo[4];
		this.booking=booking;
		this.id=customerInfo[5];

	}
//	List<String[]> ticketBooking(Map<Integer, String[]> busAvailability){
//
//		List<String[]> bookedBus = null;
//	    bookedBus=booking.searchBus(busAvailability);
//
//		return bookedBus;
//		
//	}
	
	String[] changeProfile() {
		Scanner sc=new Scanner(System.in);
		boolean end=true;
		while(end) {
		System.out.println("press 1 for change name||press 2 for change age||press 3 for change address||press 4 for change phonenumber||press 5 for change password||press 6 for save profile");
		int option=sc.nextInt();
		sc.nextLine();
		switch(option) {
		case(1):
			this.name=sc.nextLine();
		    break;
		case(2):
			this.age=sc.nextLine();
		    break;
		case(3):
			this.address=sc.nextLine();
		    break;
		case(4):
			this.phno=sc.nextLine();
		    break;
		case(5):
			this.password=sc.nextLine();
		    break;
		case(6):
			System.out.println("Saving.........");
		    end=false;
		    break;
		default:
			System.out.println("invalid number");
		}
		}
		String changedUserProfile[]= {name,age,address,phno,password,id};
		return changedUserProfile;
		
		
	}
	
	void customerComplaint() {
		System.out.println("Type your complaint hear");
		Scanner sc=new Scanner(System.in);
		String complaint=sc.nextLine();
	}
	
	void customerFeedback() {
		System.out.println("Type your feedback here");
		Scanner sc=new Scanner(System.in);
		String feedback=sc.nextLine();
	}
	
	public String[]  signup(int noOfPassenger) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter user name");
		String name=sc.nextLine();
		System.out.println("Enter age");
		String age=sc.nextLine();
		System.out.println("Enter address");
		String address=sc.nextLine();
		System.out.println("Enter phone number");
		String phno=sc.nextLine();
		System.out.println("Enter password");
		String password=sc.nextLine();
		String idNo=Integer.toString(noOfPassenger+1);
		String userDetails[]= {name,age,address,phno,password,idNo};
		boolean verification=verify.phnoVerifing(userDetails);
		if(!verification) {
			signup(noOfPassenger);
		}
		
		return userDetails;	
	}
	public void helpline() {
		System.out.println("dialing 9876543201........");
	}
	
}
class BusAdmin {
	Bus bus;
	public BusAdmin(Bus bus) {
		this.bus=bus;
	}
	public String[] setAndShowBus() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 to set details enter 2 to show details");
		int adminOption=sc.nextInt();
		String [] busDetail=null;
		if(adminOption==1) {
		busDetail=bus.setBus();}
		else if(adminOption==2) {
		bus.showBus();}
		return busDetail;
	}
	
}
class Reservation{
	
	List<String[]> gettingPassengerInfo(String[] choosedBus) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number of seats required");
		int noOfSeats=sc.nextInt();
		sc.nextLine();

		boolean check=checkingAvailability(noOfSeats,choosedBus);
		List<String[]>passengerDetails=new ArrayList<>();
		if(check) {
			for(int i=0;i<noOfSeats;i++) {
				System.out.println("Enter passenger name");
				String name=sc.nextLine();
				System.out.println("Enter gender");
				String gender=sc.nextLine();
				System.out.println("Enter age");
				String age=sc.nextLine();
//				System.out.println("Enter preffered seat number");
//				String seat=sc.nextLine();
				String passenger[]= {name,gender,age,choosedBus[0]};
				passengerDetails.add(passenger);
				}
		}
		else {
			gettingPassengerInfo(choosedBus);
		}
		return passengerDetails;
	}
	
	boolean checkingAvailability(int noOfSeats2, String[] choosedBus) {
		int noOfSeats=Integer.parseInt(choosedBus[1]);
		if(noOfSeats2<=noOfSeats) {
			return true;
	
		}
		else {
			System.out.println("Sorry Seat is not available");
			return false;

		}
	}
	
	List<String[]> searchBus(Map<Integer, String[]> busAvailability){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter from location");
		String from=sc.nextLine();
		System.out.println("Enter to location");
		String to=sc.nextLine();
		System.out.println("Enter Date in formate of dd/mm/yyyy");
		String date=sc.nextLine();
		List<String[]>resultentbus=new ArrayList<>();
		int flag=0;
		for(Map.Entry e:busAvailability.entrySet()) {
			String []arr=(String[]) e.getValue();
			if(arr[2].equals(from) && arr[3].equals(to) && arr[4].equals(date)) {
			flag=1;
			System.out.println("Press "+e.getKey()+"for booking");
			for(int i=0;i<6;i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
		}
			}
		if(flag==0) {
			System.out.println("Sorry! no bus available");
			searchBus(busAvailability);
		}
		boolean busNumber=true;
		int choosedbus=0;
		while(busNumber) {
		choosedbus=sc.nextInt();
		Set<Integer> s= busAvailability.keySet();
		boolean avalability=false;
		for(Map.Entry e:busAvailability.entrySet()) {
			if((Integer)e.getKey()==choosedbus) {
				avalability=true;
			}
		}
		if(avalability) {
			busNumber=false;
		}
		else {
			System.out.println("Invalid number");
		}
	}

		List<String[]> choosedBusDetails=gettingPassengerInfo(busAvailability.get(choosedbus));
//		int passengerAndBus[]= {choosedbus,noOfPassenger};
		return choosedBusDetails;

	}
}

class Transaction{
	Discount discountType;
	PaymentGateway billPayment;
	Transaction(Discount discount){
		this.discountType=discount;
	}
	public Transaction(PaymentGateway paymentType) {
		this.billPayment=paymentType;
		
	}
	int  wallet(int numberOfPassenger) {
		int points=numberOfPassenger*5;
		return points;
	
	}
	float discount(int busNo,int noOfPassenger, int walletPoints) {
		float finalAmount=0;
		if(busNo==123) {
			int amount=500;
			System.out.println("rupees per ticket "+amount);
			finalAmount=discountType.discountType(amount);
		}
		else if(busNo==124) {			
			int amount=250;
			System.out.println("rupees per ticket "+amount);
			finalAmount=discountType.discountType(amount);
		}
		else if(busNo==125) {			
			int amount=300;
			System.out.println("rupees per ticket "+amount);
			finalAmount=discountType.discountType(amount);
		}
		System.out.println("press 1 to use wallet points ||press 2 to continue");
		Scanner sc=new Scanner(System.in);
		int walletOption=sc.nextInt();
		if(walletOption==1) {
			finalAmount-=walletPoints;
		}
		else if(walletOption==2) {
			finalAmount=finalAmount;
		}
		return finalAmount*noOfPassenger;
	}

	List<String[]> ticketCancellation(List<String[]> busAndPassengerDetails) {
		Scanner sc=new Scanner(System.in);
		int numberOfTicket=0;
		float amount=0;
		System.out.println("Enter bus number");
		String busNo=sc.nextLine();
		System.out.println("Enter name");
		String date =sc.nextLine();
		for(String s[]:busAndPassengerDetails) {
			if(busNo.equals(s[3])&& date.equals(s[0])) {
				busAndPassengerDetails.remove(s);
				numberOfTicket+=1;
				if(busNo.equals("123")) {
					amount+=500;
				}
				else if(busNo.equals(124)) {
					amount+=250;
				}
				else if(busNo.equals(125)) {
					amount+=300;
				}
			}
		}
		refund(amount);
		return busAndPassengerDetails;
		
	}
	void refund(float amount) {
		System.out.println("Rs."+amount+" is transfered to your account");
	}
	float []billPayment(float amount,int noOfPassenger) {
		
	    float netAmount=billPayment.payableMethod(amount);
	    int walletpoints=wallet(noOfPassenger);
	    float[]amountAndWallet= {walletpoints,netAmount};
		return amountAndWallet;
		
	}
	void showTicket(float netPayment, List<String[]> busAndPassengerDetails) {
		System.out.println("Passenger Details");
		for(String[]s:busAndPassengerDetails) {
			for(int i=0;i<4;i++) {
				System.out.print(s[i]+" ");
			}
			System.out.println();
		}
		System.out.println("your bill amount"+netPayment);
		
	}

}
interface PaymentGateway{
	float payableMethod(float amount);
}
class Upi implements PaymentGateway{

	public float payableMethod(float amount) {
		System.out.println("Enter password");
		Scanner sc=new Scanner(System.in);
		int pin=sc.nextInt();
		float netAmount=amount;
		return netAmount;
		
	}
}
class NetBanking implements PaymentGateway{
	public float payableMethod(float amount) {
		System.out.println("Enter password");
		Scanner sc=new Scanner(System.in);
		String  pass=sc.nextLine();
		float netAmount = amount;
		if (amount>=500) {
			netAmount+=amount*0.01f;
		}
		return netAmount;
	}
}
class CardTransaction implements PaymentGateway{
	public float payableMethod(float amount) {
		System.out.println("enter card number");
		Scanner sc=new Scanner(System.in);
		long cardNo=sc.nextLong();
		System.out.println("Enter cvv number");
		int cvv=sc.nextInt();
		float netAmount=amount;
		if(amount>1000) {
			netAmount+=amount*0.05f;
		}
		return netAmount;
		
	}
}
interface Discount{
	float discountType(int amount);

}
class PongalDiscount implements Discount{

	@Override
	public float discountType(int amount) {
		System.out.println("bill amount "+amount+'\n'+"discount amount "+amount*0.05);
		return amount-(amount*0.05f);
		
	}
	
}
class GreatOffer implements Discount{

	@Override
	public float discountType(int amount) {
		System.out.println("bill amount "+amount+'\n'+"discount amount "+amount*0.05);
		return(amount-amount*0.1f);
	}
	
}
class RepubilcDayOffer implements Discount{

	@Override
	public float discountType(int amount) {
		System.out.println("bill amount "+amount+'\n'+"discount amount "+amount*0.05);
		return (amount-amount*0.01f);
	}
	
}
class NoDiscount implements Discount{

	@Override
	public float discountType(int amount) {
		System.out.println("Sorry no discount available");

		return amount;
	}
	
}
interface Bus{
	void seatAllocation(int noOfSeats);
    String[] setBus();
    void showBus();
	void location();
}
class Bus1 implements Bus{
	static String driverName;
	static String from;
	static String to;
	static String ac;
	static String date;
	static int seatallocation[][]= {{1,2,3,4},{5,6,7,8,},{9,10,11,12},{13,14,15,16}};

	
	@Override
	public void location() {
		System.out.println("location of bus1 is ..........");
		
		
	}

	@Override
	public void seatAllocation(int noOfSeats) {
		System.out.println("Enter your preffered seat number");
		Scanner sc=new Scanner(System.in);
		int prefferedSeats[]=new int[noOfSeats];
		int k=0;

		for(int s=0;s<noOfSeats;) {
			if(k>noOfSeats) {
				break;
			}
			for(int h=0;h<4;h++) {
				for(int l=0;l<4;l++) {
					System.out.print(seatallocation[h][l]+" ");
				}
				System.out.println();
			}
			int flag=0;
			int seat=sc.nextInt();
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(seatallocation[i][j]==seat) {
						prefferedSeats[k]=seat;
						k++;
						s++;
						seatallocation[i][j]=0;
						flag=1;
						
					}
				}
			}
			if(flag==0) {
				System.out.println("Sorry your preffered seat is already full");
			}
			
		}
		
	}

	@Override
	public String[] setBus() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter driver name");
		this.driverName=sc.nextLine();
		System.out.println("Enter from location");
		this.from=sc.nextLine();
		System.out.println("Enter to address");
		this.to=sc.nextLine();
		System.out.println("Enter ac or not");
		this.ac=sc.nextLine();
		System.out.println("Enter date");
		this.date=sc.nextLine();
		String details[]= {driverName,date,from,to,ac};
		return details;
	}

	@Override
	public void showBus() {
		System.out.println("DriverName "+ driverName);
		System.out.println("from location "+from);
		System.out.println("to location "+to);
		System.out.println("ac or not"+ac);
		
	}
		
}
class Bus2 implements Bus{
	static String driverName;
	static String from;
	static String to;
	static String ac;
	static String date;
	static int seatallocation[][]= {{1,2,3,4},{5,6,7,8,},{9,10,11,12},{13,14,15,16}};

	@Override
	public void location() {
		System.out.println("location of bus2 is..........");
		
	}

	@Override
	public void seatAllocation(int noOfSeats) {
		System.out.println("Enter your preffered seat number");
		Scanner sc=new Scanner(System.in);
		int prefferedSeats[]=new int[noOfSeats];
		int k=0;

		for(int s=0;s<noOfSeats;s++) {
			for(int h=0;h<4;h++) {
				for(int l=0;l<4;l++) {
					System.out.print(seatallocation[h][l]+" ");
				}
				System.out.println();
			}
			int flag=0;
			int seat=sc.nextInt();
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(seatallocation[i][j]==seat) {
						prefferedSeats[k]=seat;
						k++;
						seatallocation[i][j]=0;
						flag=1;
						
					}
				}
			}
			if(flag==0) {
				System.out.println("Sorry your preffered seat is already full");
			}
			
		}
		
	}

	@Override
	public String[] setBus() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter driver name");
		this.driverName=sc.nextLine();
		System.out.println("Enter from location");
		this.from=sc.nextLine();
		System.out.println("Enter to address");
		this.to=sc.nextLine();
		System.out.println("Enter ac or not");
		this.ac=sc.nextLine();
		System.out.println("Enter date");
		this.date=sc.nextLine();
		String details[]= {driverName,date,from,to,ac};
		return details;
	}

	@Override
	public void showBus() {
		System.out.println("DriverName "+ driverName);
		System.out.println("from location "+from);
		System.out.println("to location "+to);
		System.out.println("ac or not"+ac);
		
	}

	
}
class Bus3 implements Bus{
	static String driverName;
	static String from;
	static String to;
	static String ac;
	static String date;
	static int seatallocation[][]= {{1,2,3,4},{5,6,7,8,},{9,10,11,12},{13,14,15,16}};
	
	@Override
	public void location() {
		System.out.println("location of bus3 is .............");
		
	}

	@Override
	public void seatAllocation(int noOfSeats) {
		System.out.println("Enter your preffered seat number");
		Scanner sc=new Scanner(System.in);
		int prefferedSeats[]=new int[noOfSeats];
		int k=0;

		for(int s=0;s<noOfSeats;s++) {
			for(int h=0;h<4;h++) {
				for(int l=0;l<4;l++) {
					System.out.print(seatallocation[h][l]+" ");
				}
				System.out.println();
			}
			int flag=0;
			int seat=sc.nextInt();
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(seatallocation[i][j]==seat) {
						prefferedSeats[k]=seat;
						k++;
						seatallocation[i][j]=0;
						flag=1;
						
					}
				}
			}
			if(flag==0) {
				System.out.println("Sorry your preffered seat is already full");
			}
			
		}
		
	}

	@Override
	public String[] setBus() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter driver name");
		this.driverName=sc.nextLine();
		System.out.println("Enter from location");
		this.from=sc.nextLine();
		System.out.println("Enter to address");
		this.to=sc.nextLine();
		System.out.println("Enter ac or not");
		this.ac=sc.nextLine();
		System.out.println("Enter date");
		this.date=sc.nextLine();
		String details[]= {driverName,date,from,to,ac};
		return details;
	}

	@Override
	public void showBus() {
		System.out.println("DriverName "+ driverName);
		System.out.println("from location "+from);
		System.out.println("to location "+to);
		System.out.println("ac or not"+ac);
		
	}	
}
public class BusReservation {
	public static void main(String...args) {
		Scanner sc=new Scanner(System.in);
		
		List<String[]>customerLogin=new ArrayList<>();
		String user1[]= {"Dharan","20","149,K........cuddalore","9876543201","1234","1","0"};
		String user2[]= {"Kumar","19","148,K........chennai","1023456789","4321","2","0"};
		String user3[]= {"Karthic","21","150,K........coimbatore","9876543201","2589","3","0"};
		customerLogin.add(user1);
		customerLogin.add(user2);
		customerLogin.add(user3);

		Map<Integer,String[]>busAvailability=new HashMap<>();
		String bus1[]= {"123","50","Cuddalore","Chennai","25/04/2022","AC"};
		String bus2[]= {"124","19","Chennai","Banglore","26/04/2022","NONAC"};
		String bus3[]= {"125","21","Coimbatore","Tiruchi","26/04/2022","AC"};
		busAvailability.put(123, bus1);
		busAvailability.put(124, bus2);
		busAvailability.put(125, bus3);
		UserVerification verify=new UserVerification();
		
		int walletPoints=0;
		
		
		User user=new User(verify);
		
		Discount discount=new NoDiscount();
		
		Passenger customer;
		
		Reservation booking =new Reservation();
		
		Bus bus=null;
    	while(true) {
		System.out.println("Press 2 for admin||Press 1 for passenger");
		int option=sc.nextInt();

		Transaction payment=new Transaction(discount);
		List<String[]>busAndPassengerDetails = null;
		int loginuser=-1;
		
		if(option==1) {
//			boolean passengerLogin=true;
//			while(passengerLogin) {
			customer=new Passenger(verify);
			boolean logined=true;
			while(logined) {
				System.out.println("Press 1 for login||Press 2 for signup||press 3 for forgetpassword||press 4 for logout");
			    int passengerOption=sc.nextInt();
			    if (passengerOption==1) {
			    	loginuser=user.login(customerLogin);
			        
			        if(loginuser>=0) {
			        	
				        System.out.println("Password is correct "+loginuser);
//				        logined =false;
				        Passenger details=new Passenger(customerLogin.get(loginuser) ,booking);//passenger class
				        boolean login=true;
				        int busNo = 0;
				        int noOfSeatsRequired=0;
				        
				        while(login) {
				        System.out.println("Press 1 for booking ||press 2 for complaint||press 3 for feedabck||press 4 to change profile||press 5 for ticket cancellation||press 6 for see location of bus||for help line press 7");
				        int userfunction=sc.nextInt();

				        switch(userfunction) {
				        case(1):
				        	busAndPassengerDetails=booking.searchBus(busAvailability);
				        	noOfSeatsRequired=busAndPassengerDetails.size();
				        	busNo=Integer.parseInt(busAndPassengerDetails.get(0)[3]);
				        	
				        	login=false;

				            break;
				        case(2):
				        	details.customerComplaint();
				            break;
				        case(3):
				        	details.customerFeedback();
				            break;
				        case(4):
				        	int index=0;
				        	String[]changedDetails=details.changeProfile();
				            for(String[] s:customerLogin) {
				            	if(s[5].equals(changedDetails[5])) {
				            		index=customerLogin.indexOf(s);
				            		}
				            	}
				            customerLogin.set(index, changedDetails);
				            for(String [] s:customerLogin) {
				            	for(int i=0;i<6;i++) {
				            		System.out.print(s[i]+" ");
				            	}
				            	System.out.println();
				            }
				            break;
				        case(5):
				        	busAndPassengerDetails=payment.ticketCancellation(busAndPassengerDetails);
				            break;
				        case(6):
				        	bus.location();
				        break;
				        case(8):
				        	details.helpline();
				        default:
				        	System.out.println("invalid option");
				        }
				    }
				        	switch(busNo) {
				        	case(123):
				        		bus=new Bus1();
								bus.seatAllocation(noOfSeatsRequired);
								String []currBus=busAvailability.get(123);
								int noOfSeats=Integer.parseInt(currBus[1]);
								int noOfEmptySeat=noOfSeats - noOfSeatsRequired;
								String noOfSeatsRemaining=Integer.toString(noOfEmptySeat);
								String currbusno[]= {"123",noOfSeatsRemaining,"Cuddalore","Chennai","25/04/2022","AC"};
								busAvailability.replace(123,currbusno);
				        	    break;
				        	     
				        	case(124):
				        		bus=new Bus2();
				        	    bus.seatAllocation(noOfSeatsRequired);
				        	    String []currBus1=busAvailability.get(123);
								int noOfSeats1=Integer.parseInt(currBus1[1]);
								int noOfEmptySeat1=noOfSeats1 - noOfSeatsRequired;
								String noOfSeatsRemaining1=Integer.toString(noOfEmptySeat1);
								String currbusno1[]= {"123",noOfSeatsRemaining1,"Cuddalore","Chennai","25/04/2022","AC"};
								busAvailability.replace(124,currbusno1);
				        	    break;
				        	    
				        	case(125):
				        		bus=new Bus3();
				        	    bus.seatAllocation(noOfSeatsRequired);
				        	    String []currBus2=busAvailability.get(123);
								int noOfSeats2=Integer.parseInt(currBus2[1]);
								int noOfEmptySeat2=noOfSeats2 - noOfSeatsRequired;
								String noOfSeatsRemaining2=Integer.toString(noOfEmptySeat2);
								String currbusno2[]= {"123",noOfSeatsRemaining2,"Cuddalore","Chennai","25/04/2022","AC"};
								busAvailability.replace(125,currbusno2);
				        	    break;
				        	default:
				        		System.out.println("invalid number");
				        	}
				        	float amount=payment.discount(busNo,busAndPassengerDetails.size(),walletPoints);
				        	PaymentGateway paymentType = null;
				        	System.out.println("Press 1 for UPI||Press 2 for NetBanking||Press 3 for CardTransaction");
				        	int paymentOption=sc.nextInt();
				        	float  netPayment=0;
				        	switch(paymentOption) {
				        	case(1):
				        		paymentType=new Upi();

				        	    break;				        	
				        	case(2):
				        		paymentType=new NetBanking();

				        	    break;
				        	case(3):
				        		paymentType=new CardTransaction();

				        	    break;
				        	default:
				        		System.out.println("Invalid option");				        	
				        	}
				        	Transaction bill=new Transaction(paymentType);
				        	float AmountAndwalletPoints[]=bill.billPayment(amount,noOfSeatsRequired);
				        	netPayment=AmountAndwalletPoints[1];
				        	payment.showTicket(netPayment,busAndPassengerDetails);
				        	String wallet=Float.toString(AmountAndwalletPoints[0]);
//				        	loginuser
				        	
				        	String []currLoginUser=customerLogin.get(loginuser);
				        	currLoginUser[6]=wallet;
				        	customerLogin.set(loginuser, currLoginUser);
				        	
				        }

			        else{
				        System.out.println("Please check login details");
				    }
			    }			   
			    else if(passengerOption==2) {
				    customerLogin.add(customer.signup(customerLogin.size()));				  //sign up method
			    }
			    else if(passengerOption==3) {
			    	int index=0;
			    	String[] newPassword=user.forgetPassword(customerLogin);
			    	for(String[]s:customerLogin) {
			    		if(s[5].equals(newPassword[5])) {
			    			index=customerLogin.indexOf(s);		    			
							customerLogin.set(index,newPassword);
			    		}
			    	}			    	
			    }
			    else if(passengerOption==4) {
			    	logined=false;
			    	user.logout();
			    	break;
			    }
		    }
		}
		else if(option==2) {
			List<String[]>adminLogin=new ArrayList<>();
			String admin[]= {"Dharankumar","20","149,K........cuddalore","9876543201","1234"};
			adminLogin.add(admin);
			int adminuser=-1;
			adminuser=user.login(adminLogin);     
	        if(adminuser>=0) {
		        System.out.println("Password is correct "+adminuser);
//		          Passenger details=new Passenger(customerlogin.get(loginuser),booking);//passenger class

		    boolean adminLogout=true;
		    while(adminLogout) {
		        System.out.println("Enter 1 for discount ||Enter 2 for set Bus||Press 3 for logout");
		    int adminOpt=sc.nextInt();
		    switch(adminOpt) {
		    case(1):
	        System.out.println("press 1 for pongal offer||press 2 for greatoffer||press 3 for Republicday Offer");
	        int discountOption=sc.nextInt();
	        switch(discountOption) {
	        case(1):
	        	discount=new PongalDiscount();
	            break;
	        case(2):
	        	discount=new GreatOffer();
	            break;
	        case(3):
	        	discount=new RepubilcDayOffer();
	            break;
	        default:
	        	System.out.println("Invalid option");
	        }
	        break;
		    case(2):
	        boolean adminOption=true;
	        while(adminOption) {
		    System.out.println("Press 1 for Bus1||Press 2 for Bus2||Press 3 for Bus3||press 4 for cancel");
	        int busInfo=sc.nextInt();
	        int busNumber = 0;
	        switch(busInfo) {
	        case(1):
	        	bus=new Bus1();
	            busNumber=123;	            
	            break;
	        case(2):
	        	bus=new Bus2();
	            busNumber=124;
	            break;
	        case(3):
	        	bus=new Bus3();
	            busNumber=125;
	            break;
	        case(4):
	        	adminOption=false;
	            break;
	        default:
	        	System.out.println("Invalid option");
	        }
	        BusAdmin userAdmin=new BusAdmin(bus);
	        if(busNumber==0) {
	        	continue;
	        }
	        else {
	        String busDetails[]=userAdmin.setAndShowBus();
//	        {"125","21","Coimbatore","Tiruchi","26/04/2022","AC"};
//	        {driverName,date,from,to,ac};
	        String[] busInformation=busAvailability.get(busNumber);
	        busInformation[4]=busDetails[1];
	        busInformation[2]=busDetails[2];
	        busInformation[3]=busDetails[3];
	        busInformation[5]=busDetails[4];
	        busAvailability.replace(busNumber, busInformation);
	            }
	        }
	        for(Map.Entry e:busAvailability.entrySet()) {
				System.out.println(e.getKey());
				String []temparr=(String[]) e.getValue();
				for(int i=0;i<6;i++) {
					System.out.print(temparr[i]+" ");
				}
				System.out.println();
			}
	        break;
		    case(3):
		    	user.logout();
		        adminLogout=false;
		        break;
		    }
	        
	    }
		    }
	        else{
		        System.out.println("Please check login details");
		    }
			
		}
	    }
	}
	}



