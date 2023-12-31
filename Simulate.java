package simulation;
import java.util.Random;

class Vehicle implements Runnable
{
   private int id; // id identifies each vehicle
   private static TollBooth toll =new TollBooth();  // only one tollbooth
   public Vehicle(int id)
   {
      // each vehicle has a different  identifying number
	  this.id=id;
   }
    public void run()
    {
	    System.out.println("Vehicle " +(id+1)+" starts journey");
		Random RandGen= new Random();
		int Rnd= RandGen.nextInt(100);
		travel(Rnd);   // time taken from strating booth to toll booth
        System.out.println("Vehicle "+(id+1)+" arrives at the toll");
		toll.useToll(this);  // current vehicle uses tollbooth
		travel(Rnd); // time taken to cross bridge
		System.out.println("Vehicle" +(id+1)+ " has crossed bridge");
	}	
      public int getVehicleId()
	  {
	     return this.id;
	  }
	  public void travel(int time)
	  {
	     int limit= 500000;
		 for(int j=0; j<time; j++)
		 {
		    for(int k=0;k<limit; k++)
			{
			  // do nothing
			}

		 }
	  }
}
class TollBooth
{
  boolean inUse;
  public TollBooth()
  {
    inUse=false;
  }
  
  public void useToll(Vehicle vehicle)
  {
     while(true)
	 {
	    if(inUse==false)
		{
		  synchronized (this)
		  {
		     inUse=true;
			 System.out.println("Vehicle" +(vehicle.getVehicleId()+1)+ " enters tollbooth");
			 vehicle.travel(50); // vehicle spends 50 times units in the tollbooth
			 System.out.println("Vehicle" +(vehicle.getVehicleId()+1)+ " exits tollbooth");
			 
			 inUse=false;
			 break;
		  }
		}
	 }
  
  }
}
public class Simulate
{
  private static int noOfVehicle=5;
  private static Vehicle[] vehicles;
  
  public static void main(String[] args)
  {
    try
	{
	   Simulate sm= new Simulate();
	   vehicles=new Vehicle[5];
	   for(int i=0; i<noOfVehicle;i++)
	   {
	      vehicles[i]=new Vehicle(i);
		  Thread t=new Thread(vehicles[i]);
		  t.start();
		  Thread.sleep(1000);
	   }
	}
     catch(Exception ex)
	 {
	   System.out.println(ex);
	 }
  }

}