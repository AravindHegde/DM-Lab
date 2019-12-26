import java.util.*;
import java.io.*;
public class NaiveBayes {
	public static ArrayList<Data1> dataset;
	public static double mean(String tag) {
		double mean=0;
		int count=0;
		for(int i=0;i<dataset.size();i++) {
			if(dataset.get(i).cat.equals(tag)) {
				mean+=dataset.get(i).temp;
				count++;
			}
		}
		return mean/count;
	}
	public static double sd(String tag,double mean) {
		int ans=0;
		int count=0;
		for(int i=0;i<dataset.size();i++) {
			if(dataset.get(i).cat.equals(tag)){
				ans+=Math.pow(dataset.get(i).temp-mean, 2);
				count++;
			}
		}
		return Math.sqrt(ans/((count-1)));
	}
	public static void main(String[] args) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		BufferedReader bf=new BufferedReader(new FileReader(new File("/root/fever.csv")));
		String line=bf.readLine();
		dataset=new ArrayList<>();
		while(line!=null) {
			String row[]=line.split(",");
			Data1 ds=new Data1();
			ds.index=Integer.parseInt(row[0]);
			ds.temp=Double.parseDouble(row[1]);
			ds.cat=row[2];
			dataset.add(ds);
			line=bf.readLine();
		}
		double exp1=0,exp2=0,p1=0,p2=0,pm1=0,pm2=0,sd1=0,sd2=0,res1=0,res2=0,mean1=0,mean2=0;
		for(int i=0;i<dataset.size();i++) {
			if(dataset.get(i).cat.equals("yes")) {
				pm1++;
			}
			else {
				pm2++;
			}
		}
		System.out.println("Enter Temperature");
		Scanner sc=new Scanner(System.in);
		double t=sc.nextDouble();
		pm1=pm1/dataset.size();
		pm2=pm2/dataset.size();
		mean1=mean("yes");
		mean2=mean("no");
		sd1=sd("yes",mean1);
		sd2=sd("no",mean2);
		exp1=Math.pow(t-mean1,2)/(2*Math.pow(sd1, 2));
		p1=Math.exp(-1*exp1)/(sd1*Math.sqrt(2*3.14));
		exp2=Math.pow(t-mean2,2)/(2*Math.pow(sd2, 2));
		p2=Math.exp(-1*exp2)/(sd2*Math.sqrt(2*3.14));
		res1=p1*pm1;
		res2=p2*pm2;
		System.out.println("Probability of having fever is "+res1);
		System.out.println("Probability of not having fever is "+res2);
		if(res1>=res2) {
			System.out.println("person with temperature"+t+"  have fever");
		}
		else {
			System.out.println("person with temperature"+t+" doesnt have fever");
		}
	}

}
class Data1{
	int index;
	double temp;
	String cat;
}
