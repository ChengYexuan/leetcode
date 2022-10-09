package test;

public class SuperClass {

    static {
        System.out.println("Static initializer of father.");
    }

    static int number = 10;

    {
        System.out.println("Non-static initializer of father.");
    }

    int count = 1;

    public SuperClass(){
        System.out.println("Constructor of father.");
    }
}
