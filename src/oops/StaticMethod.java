package oops;

class StaticMethod {
    int instVar = 5;
    
    void displayInstanceVariable() {
        System.out.println("Instance variable : " + instVar);
    }
    static void AccessVar(StaticMethod obj) {
       
        System.out.println("Static method accessing instance variable: " + obj.instVar);
        obj.displayInstanceVariable();
    }

    public static void main(String[] args) {
      
        StaticMethod obj = new StaticMethod();
        AccessVar(obj);
        obj.instVar = 10;
        obj.displayInstanceVariable();
    }
}
