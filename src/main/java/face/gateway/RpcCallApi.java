package face.gateway;

public interface RpcCallApi {
    boolean isSupport(Integer apiType, Integer rpcType);


    Result<String> call(Integer apiType, Integer rpcType);
}
