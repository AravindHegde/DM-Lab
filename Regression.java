import java.util.*;
import java.io.*;
public class Regression {

	public static void main(String[] args) throws FileNotFoundException,IOException{
		Scanner sc=new Scanner(System.in);
		BufferedReader csv=new BufferedReader(new FileReader(new File("/root/regression.csv")));
		String line=csv.readLine();
		ArrayList<Double>x=new ArrayList<>();
		ArrayList<Double>y=new ArrayList<>();
		double meanx=0,meany=0,covxy=0,varx=0,m=0,c=0;
		while(line!=null) {
			String row[]=line.split(",");
			x.add(Double.parseDouble(row[0]));
			y.add(Double.parseDouble(row[1]));
			line=csv.readLine();
		}
		for(int i=0;i<x.size();i++) {
			meanx+=x.get(i);
			meany+=y.get(i);
		}
		meanx/=x.size();
		meany/=y.size();
		for(int i=0;i<x.size();i++) {
			varx+=Math.pow(meanx-x.get(i),2);
			covxy+=(meanx-x.get(i))*(meany-y.get(i));
		}
		varx=varx/x.size();
		covxy=covxy/y.size();
		m=covxy/varx;
		c=meany-m*meanx;
		System.out.println("Enter x value");
		double val=sc.nextDouble();
		double ans=m*val+c;
		System.out.println("Ans is "+ans);
	}

}
