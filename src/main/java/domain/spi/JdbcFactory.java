package domain.spi;

import java.util.ServiceLoader;

public class JdbcFactory {
    private static ServiceLoader<Jdbc> jdbcServices = ServiceLoader.load(Jdbc.class);

    public static Jdbc getJdbc(String name) {
        for (Jdbc jdbc : jdbcServices) {
            if (jdbc.isSupportType(name)) {
                return jdbc;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Jdbc jdbc = getJdbc("mysql");
        System.out.println(jdbc.getConnection());
    }
}