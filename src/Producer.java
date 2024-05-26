class Producer implements Runnable {
    Store store;

    Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            store.Add();
            Thread.sleep(1000); // засыпаем на секунд
        } catch (Exception e) {
        }
        System.out.println("Producer thread завершен");
    }
}



