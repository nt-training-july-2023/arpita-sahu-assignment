package MultiThreading;

import java.util.Scanner;

public class ThreadAcknowledgement {

    static class ReadThread extends Thread {
        private String threadName;

        public ReadThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a message: ");
            String message = sc.nextLine();
            sc.close();
            System.out.println("Thread " + threadName + " received: " + message);
        }
    }

    public static void main(String[] args) {
        ReadThread thread1 = new ReadThread("One");
        ReadThread thread2 = new ReadThread("Two");

        thread1.start();
        thread2.start();
    }
}
