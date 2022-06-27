package face.gateway;

import java.util.Random;

public class Rpc {

    public static void main(String[] args) {
        int apiType = new Random().nextInt(2);
        int rpcType = new Random().nextInt(2);

        rpcCall(apiType, rpcType);
    }

    public static String rpcCall(Integer apiType, Integer rpcType) {
        RpcCallApi rpcCallApi = RpcManager.getRealRpcCall(apiType, rpcType);
        if (rpcCallApi == null) {
            throw new IllegalArgumentException("Unsupport api type.");
        }
        Result<String> result = rpcCallApi.call(apiType, rpcType);
        return result.data;

    }
}
