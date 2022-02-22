package spi;

public class MysqlJdbc implements Jdbc {

    @Override
    public boolean isSupportType(String type) {
        return "mysql".equals(type);
    }

    @Override
    public String getConnection() {
        return "mysql connection";
    }
}