package test;

public class ChildClass extends SuperClass{

    static {
        System.out.println("Static initializer of child.");
    }
    {
        System.out.println("Non-static initializer of child");
    }

    public ChildClass(){
        System.out.println("Constructor of child.");
    }
}
