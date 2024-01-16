class OuterClass {
    private int variable1;
    private String variable2;

    public OuterClass(int variable1, String variable2) {
        this.variable1 = variable1;
        this.variable2 = variable2;
    }

    public OuterClass(int variable1) {
        this.variable1 = variable1;
        this.variable2 = null;
    }


    class InnerClass {

        public void displayOuterClassVariables() {
            System.out.println("Variable1 from OuterClass: " + variable1);
            System.out.println("Variable2 from OuterClass: " + variable2);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass outerObj1 = new OuterClass(10, "Hello");
        OuterClass outerObj2 = new OuterClass(20);
        OuterClass.InnerClass innerObj = outerObj1.new InnerClass();
        innerObj.displayOuterClassVariables();
    }
}
