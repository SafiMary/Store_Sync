//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Store  store = new Store();
        Producer producer = new Producer(store);
        new Thread(producer).start();

    }
}