package face.gateway;

public abstract class Rpc1CallImpl implements RpcCallApi {


    @Override
    public Result<String> call(Integer apiType, Integer rpcType) {
        return this.realCall(rpcType);

    }

    protected abstract Result<String> realCall(Integer rpcType);
}
