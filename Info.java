

public class Info{
  public String flight;
  public String from;
  public String to;
  public int requestTime;
  public int numPassengers;
  public int actualTime;
  public int invocation;
  public int remainder;

  public Info(){}

  public Info(String num, String f, String t, int time, int passengers, int selection){
    flight = num;
    from = f;
    to = t;
    requestTime = time;
    numPassengers = passengers;

    remainder = (passengers/2)%60;
    if(remainder == 0){
      actualTime = time + (passengers/120);
    }
    else{
      actualTime = time + (passengers/120) + 1;
    }
    if(selection == 1){
      invocation = time;
    }
    else if(selection == 2){
      invocation = passengers;
    }
    else{
      invocation = actualTime;
    }
  }
}
