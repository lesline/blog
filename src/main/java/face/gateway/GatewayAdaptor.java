package face.gateway;

import java.util.Random;


public class GatewayAdaptor {

    public static void main(String[] args) {
        int apiType = new Random().nextInt(2);
        int rpcType = new Random().nextInt(2);

        rpcCall(apiType, rpcType);
    }

    public static String rpcCall(Integer apiType, Integer rpcType) {

        if (apiType == 0) {
            if (rpcType == 0) {
                Rpc1_1 rpc = new Rpc1_1(String.valueOf(rpcType));
                try {
                    Response1_1 receive = Rpc1_1.receive(rpc);
                    return receive.getData();
                } catch (RuntimeException e) {
                    throw new RuntimeException("RPC 1-1 call failed.");
                }
            }
            if (rpcType == 1) {
                Rpc1_2 rpc = new Rpc1_2(String.valueOf(rpcType));
                Response1_2 receive = Rpc1_2.receive(rpc);
                if (!receive.isSucceed()) {
                    throw new RuntimeException("RPC 1-2 call failed.");
                }
                return receive.getData2();
            }

        } else if (apiType == 1) {
            if (rpcType == 0) {
                Rpc2_1 rpc = new Rpc2_1(String.valueOf(rpcType));
                Response2_1 receive = Rpc2_1.receive(rpc);
                if (receive.isFailed()) {
                    throw new RuntimeException("RPC 2-1 call failed.");
                }
                return receive.getData3();
            }
            if (rpcType == 1) {
                Rpc2_1 rpc = new Rpc2_1(String.valueOf(rpcType));
                Response2_1 receive = Rpc2_1.receive(rpc);
                if (receive.isFailed()) {
                    throw new RuntimeException("RPC 2-2 call failed.");
                }
                return receive.getData3();
            }
        }

        throw new IllegalArgumentException("Unsupport api type.");
    }

    public static class Rpc1_1 {

        private String data1;

        public Rpc1_1(String data1) {
            this.data1 = data1;
        }

        public static Response1_1 receive(Rpc1_1 t1) {
            boolean succeed = new Random().nextBoolean();
            if (succeed) {
                Response1_1 response11 = new Response1_1();
                response11.setData("succeed");
                return response11;
            }

            throw new RuntimeException("failed");
        }
    }

    public static class Response1_1 {

        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class Rpc1_2 {

        private String data2;

        public Rpc1_2(String data2) {
            this.data2 = data2;
        }

        public static Response1_2 receive(Rpc1_2 t2) {
            boolean succeed = new Random().nextBoolean();
            Response1_2 response2 = new Response1_2();
            response2.setData(succeed ? "succeed" : "failed");
            response2.setSucceed(succeed);
            return response2;
        }
    }

    public static class Response1_2 {

        private boolean succeed;
        private String data2;

        public String getData2() {
            return data2;
        }

        public void setData(String data) {
            this.data2 = data;
        }

        public boolean isSucceed() {
            return succeed;
        }

        public void setSucceed(boolean succeed) {
            this.succeed = succeed;
        }
    }

    public static class Rpc2_1 {

        private String data3;

        public Rpc2_1(String data3) {
            this.data3 = data3;
        }

        public static Response2_1 receive(Rpc2_1 t3) {
            boolean failed = new Random().nextBoolean();
            Response2_1 response21 = new Response2_1();
            response21.setData3(failed ? "failed" : "succeed");
            response21.setFailed(failed);
            return response21;
        }
    }

    public static class Response2_1 {

        private boolean failed;
        private String data3;

        public String getData3() {
            return data3;
        }

        public void setData3(String data3) {
            this.data3 = data3;
        }

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(boolean failed) {
            this.failed = failed;
        }
    }

    public static class Rpc2_2 {

        private String data3;

        public Rpc2_2(String data3) {
            this.data3 = data3;
        }

        public static Response2_1 receive(Rpc2_1 t3) {
            boolean failed = new Random().nextBoolean();
            Response2_1 response21 = new Response2_1();
            response21.setData3(failed ? "failed" : "succeed");
            response21.setFailed(failed);
            return response21;
        }
    }

    public static class Response2_2 {

        private boolean failed;
        private String data2_2;

        public String getData2_2() {
            return data2_2;
        }

        public void setData2_2(String data2_2) {
            this.data2_2 = data2_2;
        }

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(boolean failed) {
            this.failed = failed;
        }
    }
}
