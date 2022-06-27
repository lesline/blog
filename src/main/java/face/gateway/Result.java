package face.gateway;

public class Result<T> {
    boolean isSuccess;
    String code;
    String message;

    T data;

    public Result() {
        isSuccess = true;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

}
