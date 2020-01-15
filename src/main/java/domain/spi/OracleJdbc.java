package domain.spi;

public class OracleJdbc implements Jdbc {

    @Override
    public boolean isSupportType(String type) {
        return "oracle".equals(type);
    }

    @Override
    public String getConnection() {
        return "oracle connection";
    }
}