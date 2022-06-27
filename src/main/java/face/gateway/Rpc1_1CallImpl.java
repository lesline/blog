package face.gateway;

public class Rpc1_1CallImpl extends Rpc1CallImpl {
    @Override
    public boolean isSupport(Integer apiType, Integer rpcType) {
        if (apiType == 0) {
            if (rpcType == 0) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Result<String> realCall(Integer rpcType) {
        Result<String> result = new Result();

        //
        GatewayAdaptor.Rpc1_1 rpc = new GatewayAdaptor.Rpc1_1(String.valueOf(rpcType));
        try {
            GatewayAdaptor.Response1_1 receive = GatewayAdaptor.Rpc1_1.receive(rpc);
            result.setData(receive.getData());

        } catch (RuntimeException e) {
            // throw new RuntimeException("RPC 1-1 call failed.");
            // log.error("");
            result = new Result<>("001", "RPC 1-1 call failed.");

        }
        return result;

    }
}
