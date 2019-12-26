import java.util.Scanner;

public class Confusion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter Confusion matrix");
		int tp,tn,fp,fn;
		Scanner sc=new Scanner(System.in);
		tp=sc.nextInt();
		fn=sc.nextInt();
		fp=sc.nextInt();
		tn=sc.nextInt();
		System.out.println("\t\tPredicted class\n");
		System.out.println("\t\t"+tp+"\t"+fn);
		System.out.println("Actual Class");
		System.out.println("\t\t"+fp+"\t"+tn);
		double accuracy=(double)(tp+tn)/(tp+fp+fn+tn);
		double sensitivity,recall;
		sensitivity=recall=(double)tp/(tp+fn);
		double specificity=(double)tn/(tn+fp);
		double precesion=(double)tp/(tp+fp);
		System.out.println("Acuuracy :"+accuracy);
		System.out.println("Sensitivity :"+sensitivity);
		System.out.println("specificity :"+specificity);
		System.out.println("recall :"+recall);
		System.out.println("precesion :"+precesion);
	}

}
