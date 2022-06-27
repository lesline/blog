package face;

public class Singlton {
    public static void main(String[] args) {

        System.out.println(Singleton.getInstacne());
    }
}


class Singleton {
    private static volatile Singleton singleton;

    public static Singleton getInstacne() {
        if (singleton != null) {
            return singleton;
        }
        synchronized (Singleton.class) {
            if (singleton != null) {
                return new Singleton();
            }
        }
        return singleton;
    }
}


class Singleton2 {
    private static class SingletonHolder {
        private final static Singleton2 single = new Singleton2();
    }

    public static final Singleton2 getInstance() {
        return SingletonHolder.single;
    }

}





