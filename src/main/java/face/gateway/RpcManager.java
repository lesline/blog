package face.gateway;

import java.util.ArrayList;
import java.util.List;

public class RpcManager {
    private static List<RpcCallApi> prcCallList = new ArrayList<>();

    static {
        prcCallList.add(new Rpc1_1CallImpl());
    }


    public static RpcCallApi getRealRpcCall(Integer apiType, Integer rpcType) {

        for (RpcCallApi api : prcCallList) {
            if (api.isSupport(apiType, rpcType)) {
                return api;
            }
        }
        return null;
    }
}
