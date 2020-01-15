package domain.spi;

public interface Jdbc {
    boolean isSupportType(String type);
    String getConnection();
}