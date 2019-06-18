package lesson4;

public class PrintABC {
    volatile int threadIndex = 0;

    public static void main(String[] args) {
        PrintABC p = new PrintABC();
        new A(p).start();
        new B(p).start();
        new C(p).start();

    }
}

class A extends Thread {
    PrintABC p;

    public A(PrintABC p) {
        this.p = p;
    }

    @Override
    public void run() {
        try {
            synchronized (p) {
                for (int i = 0; i < 5; i++) {
                    while (p.threadIndex != 0) {
                        try {
                            p.wait();
                        } catch (InterruptedException e) {e.printStackTrace();}
                    }
                    System.out.print("A");
                    p.threadIndex = 1;
                    p.notifyAll();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}


class B extends Thread {
    PrintABC p;

    public B(PrintABC p) {
        this.p = p;
    }

    @Override
    public void run() {
        try {
            synchronized (p) {
                for (int i = 0; i < 5; i++) {
                    while (p.threadIndex != 1) {
                        try {
                            p.wait();
                        } catch (InterruptedException e) {e.printStackTrace();}
                    }
                    System.out.print("B");
                    p.threadIndex = 2;
                    p.notifyAll();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}


class C extends Thread {
    PrintABC p;

    public C(PrintABC p) {
        this.p = p;
    }

    @Override
    public void run() {
        try {
            synchronized (p) {
                for (int i = 0; i < 5; i++) {
                    while (p.threadIndex != 2) {
                        try {
                            p.wait();
                        } catch (InterruptedException e) {e.printStackTrace();}
                    }
                    System.out.print("C");
                    p.threadIndex = 0;
                    p.notifyAll();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}