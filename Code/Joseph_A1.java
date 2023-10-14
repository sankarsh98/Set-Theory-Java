package Code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

enum NumberType{
    One(1,"un","satu","uno"), 
    Two(2,"deux","dua","dos"),
    Three(3,"trois","tiga","tres"),
    Four(4,"quatre","empat","cuatro"),
    Five(5, "cinq", "lima", "cinco"),
    Six(6, "six", "enam", "seis"),
    Seven(7, "sept", "tujuh", "siete"),
    Eight(8, "huit", "delapan", "ocho"),
    Nine(9, "neuf", "sembilan", "neuve"),
    Ten(10, "dix", "sepuluh", "diez");

    public final int number;
    public final String french;
    public final String spanish;
    public final String bahasa;

    private NumberType(int number,String french, String spanish, String bahasa){
        this.number=number;
        this.french=french;
        this.spanish=spanish;
        this.bahasa=bahasa;
    }


}

class Set{
    private ArrayList<NumberType> s;

    public Set() {
        this.s = new ArrayList<NumberType>();
    }

    public Set(Set otherSet){
        this.s = new ArrayList<>(otherSet.s);
    }

    public boolean isEmpty(){
        return this.cardinality()==0;
    }

    public int cardinality(){
        return this.s.size();
    }

    public boolean belongTo(NumberType element){
        return this.s.contains(element);
    }

    public void addElement(NumberType element){
        if (this.belongTo(element)){
            // no action
        }else{
            this.s.add(element);
        }
    }

    public boolean subset(Set otherSet){
        boolean flag = true;

        for(int i=0;i<this.cardinality();i++){
            if(otherSet.belongTo(this.s.get(i))){
                //no action
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void union(Set otherSet){
        for(int i=0;i<otherSet.cardinality();i++){
            if(this.belongTo(otherSet.s.get(i))){
                //no action
            }else{
                this.addElement(otherSet.s.get(i));
            }
        }
    }

    public void intersection(Set otherSet){
        ArrayList<NumberType> intersection = new ArrayList<>();
        for(int i=0;i<this.cardinality();i++){
            if(otherSet.belongTo(this.s.get(i))){
                intersection.add(this.s.get(i));
            }
        }
        this.s=intersection;
    }
     
    public void difference(Set otherSet){
        ArrayList<NumberType> differenceSet = new ArrayList<>();
        for(int i=0;i<this.cardinality();i++){
            if(!otherSet.belongTo(s.get(i))){
                differenceSet.add(this.s.get(i));
            }
        }
        this.s=differenceSet;
    }

    public Set complement(){
        Set complementSet=new Set();
        for(NumberType num:NumberType.values()){
            if(this.belongTo(num)){
                //no action
            }else{
                complementSet.addElement(num);
            }
        }
        return complementSet;
    }

    public boolean equality(Set otherSet){

        if(this.subset(otherSet) && otherSet.subset(this)){
            return true;
        }
        return false;
    }

    public String toString(){
        String result="{";
        for(int i=0;i<this.cardinality();i++){
            result = result + this.s.get(i);
            if(i!=this.cardinality()-1){
                result = result + ", ";
            }
        }
        result=result+"}";
        return result;
    }

    public String getNumberInfoFormat(){
        String result = "{";
        Random random = new Random();
        ArrayList<String> labels = new ArrayList<>(Arrays.asList("number","french","bahasa","spanish"));
        String randomString = labels.get(random.nextInt(labels.size()));
        switch (randomString){
            case "number":
                for(int i=0;i<this.cardinality();i++){
                    result = result + this.s.get(i).number;
                    if(i!=this.cardinality()-1){
                        result = result + ", ";
                    }
                }
                result=result+"}";
                break;
            case "french":
                for(int i=0;i<this.cardinality();i++){
                    result = result + this.s.get(i).french;
                    if(i!=this.cardinality()-1){
                        result = result + ", ";
                    }
                }
                result=result+"}";
                break;
            case "bahasa":
                for(int i=0;i<this.cardinality();i++){
                    result = result + this.s.get(i).bahasa;
                    if(i!=this.cardinality()-1){
                        result = result + ", ";
                    }
                }
                result=result+"}";
                break;
            case "spanish":
                for(int i=0;i<this.cardinality();i++){
                    result = result + this.s.get(i).spanish;
                    if(i!=this.cardinality()-1){
                        result = result + ", ";
                    }
                }
                result=result+"}";
                break;
        }   
        return result;
    }

}

public class Joseph_A1{
    private static Scanner input=new Scanner(System.in);

    private static void displayNumberTypeInfo(){
        System.out.println("");
        System.out.printf("%-14s%-14s%-14s%-14s%-14s%n", "Number Type", "Number", "French","Bahasa","Spanish");
        for(NumberType number:NumberType.values()){
            System.out.printf("%-14s%-14s%-14s%-14s%-14s%n", number, number.number, number.french,number.bahasa,number.spanish);
        }
        System.out.println("");
    }

    private static NumberType getAnElement(){
        Random random=new Random();
        int randomInt = random.nextInt(10);
        return NumberType.values()[randomInt];
    }

    private static Set getASet(){
        Random random=new Random();
        int randomInt = random.nextInt(1,10);
        Set s = new Set();
        for(int i=0;i<=randomInt;i++){
            s.addElement(getAnElement());
        }
        return s;
    }

    private static void displayMenu(){
        System.out.println("Welcome to SIM Set Theory lesson\n");
        System.out.println("0: Properties of set");
        System.out.println("1: Union example");
        System.out.println("2: Intersection example");
        System.out.println("3: Subset example");
        System.out.println("4: Difference example");
        System.out.println("5: Complement example");
        System.out.println("6. Sets equality example");
        System.out.println("7: Distributive Law 1");
        System.out.println("8: Distributive Law 2");
        System.out.println("9: Quit\n");
        System.out.print("Your option: ");
    }

    private static void unionExample(){
        System.out.println("Given sets");
        Set A = getASet();
        Set B = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        A.union(B);
        System.out.println("\tUnion of A and B = "+A.toString());
        System.out.println("");
    }

    private static void intersectionExample(){
        System.out.println("Given sets");
        Set A = getASet();
        Set B = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        A.intersection(B);
        System.out.println("\tIntersection of A and B = "+A.toString());
        System.out.println("");
    }

    private static void subsetExample(){
        System.out.println("Given sets");
        Set A = getASet();
        Set B = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        System.out.println("Conclusion");
        System.out.println("\tA subset of B: "+A.subset(B));
        System.out.println("\tB subset of A: "+B.subset(A));
        System.out.println("");
    }

    private static void differenceExample(){
        System.out.println("Given sets");
        Set A = getASet();
        Set B = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        System.out.println("");
        A.difference(B);
        System.out.println("\tA - B = "+A.toString());
        System.out.println("");
    }

    private static void complementExample(){
        System.out.println("Given set");
        Set A = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("");
        Set C = A.complement();
        System.out.println("\tA' = "+C.toString());
        System.out.println("");
    }

    private static void equalityExample(){
        System.out.println("Given sets");
        Set A = getASet();
        Set B = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        System.out.println("");
        System.out.println("Analysis");
        System.out.println("\tA subset of B: "+A.subset(B));
        System.out.println("\tB subset of A: "+B.subset(A));
        System.out.println("");
        System.out.println("Conclusion");
        System.out.println("\tA equals to B: "+A.equality(B));
        System.out.println("");
    }

    private static void distributiveLaw_1(){
        System.out.println("We wish to prove: A U (B I C) = (A U B) I (A U C)\n");
        Set A = getASet();
        Set B = getASet();
        Set C = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        System.out.println("\tC = "+C.toString());

        Set LA = new Set(A);
        Set LB = new Set(B);
        Set LC = new Set(C);

        LB.intersection(LC);
        LA.union(LB);

        System.out.println("\nLHS analysis");
        System.out.println("\tLHS = "+LA.toString());

        Set RA1 = new Set(A);
        Set RA2 = new Set(A);
        Set RB = new Set(B);
        Set RC = new Set(C);

        RA1.union(RB);
        RA2.union(RC);
        RA1.intersection(RA2);

        System.out.println("\nRHS analysis");
        System.out.println("\tRHS = "+RA1.toString());

        System.out.println("\nConclusion");
        System.out.println("\tLHS = RHS is "+LA.equality(RA1));
        System.out.println("");
    }

    private static void distributiveLaw_2(){
        System.out.println("We wish to prove: A I (B U C) = (A I B) U (A I C)\n");
        Set A = getASet();
        Set B = getASet();
        Set C = getASet();
        System.out.println("\tA = "+A.toString());
        System.out.println("\tB = "+B.toString());
        System.out.println("\tC = "+C.toString());

        Set LA = new Set(A);
        Set LB = new Set(B);
        Set LC = new Set(C);

        LB.union(LC);
        LA.intersection(LB);

        System.out.println("\nLHS analysis");
        System.out.println("\tLHS = "+LA.toString());

        Set RA1 = new Set(A);
        Set RA2 = new Set(A);
        Set RB = new Set(B);
        Set RC = new Set(C);

        RA1.intersection(RB);
        RA2.intersection(RC);
        RA1.union(RA2);

        System.out.println("\nRHS analysis");
        System.out.println("\tRHS = "+RA1.toString());

        System.out.println("\nConclusion");
        System.out.println("\tLHS = RHS is "+LA.equality(RA1));
        System.out.println("");
    }

    public static void displaySubmenu(){
        System.out.println("Some basic operations in set");
        System.out.println("\t1. Add an element");
        System.out.println("\t2. Check an element");
        System.out.println("\t3. Cardinality");
        System.out.println("\t4. Number Info Format");
        System.out.println("\t9. Quit\n");
    }

    public static void anExample(){

    }

    public static void main(String[] args){

        displayNumberTypeInfo();

        String menuInput = "";
        String subMenuInput = "";
        while(!menuInput.equals("9")){
            displayMenu();
            menuInput=input.nextLine();
            System.out.println("");
            switch(menuInput){
                case "0":
                    System.out.println("Here is an example of set");
                    Set s = getASet();
                    System.out.println("\tA = "+s.toString());
                    System.out.println("\tAll elements in set are distinct and random order\n");
                    subMenuInput="";
                    while(!subMenuInput.equals("9")){
                        displaySubmenu();
                        System.out.print("Enter your option: ");
                        subMenuInput=input.nextLine();
                        System.out.println("");
                        switch(subMenuInput){
                            case "1":
                                System.out.print("Enter an element : ");
                                String number = input.nextLine();
                                s.addElement(NumberType.valueOf(number));
                                System.out.println("==> A = "+s.toString());
                                System.out.println("");
                                break;

                            case "2":
                                System.out.print("Enter an element : ");
                                String checkNumber = input.nextLine();
                                NumberType checkNumberType = NumberType.valueOf(checkNumber);

                                if(s.belongTo(checkNumberType)){
                                    System.out.println("Element "+checkNumber+" is in set");
                                }else{
                                    System.out.println("Element "+checkNumber+" is not in set");
                                }
                                System.out.println("");
                                break;
                            case "3":
                                System.out.println("==> No of elements in set is "+s.cardinality());
                                System.out.println("");
                                break;
                            case "4":
                                System.out.println("Notation in enum format");
                                System.out.println("\tA = "+s.getNumberInfoFormat());
                                System.out.println("");
                                break;
                        }
                    }

                case "1":
                    unionExample();
                    break;
                case "2":
                    intersectionExample();
                    break;
                case "3":
                    subsetExample();
                    break;
                case "4":
                    differenceExample();
                    break;
                case "5":
                    complementExample();
                    break;
                case "6":
                    equalityExample();
                    break;
                case "7":
                    distributiveLaw_1();
                    break;
                case "8":
                    distributiveLaw_2();
                    break;
            }
        }

    }
}