package enums;

import java.util.Arrays;

import static enums.Input.DIME;
import static enums.Input.NICKEL;


public enum InputHand {
    start(NICKEL, DIME, DIME);

    InputHand(Input start, Input... types) {
        value = start;
        values = types;    //初始化获取Input列表
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"value\":")
                .append(value);
        sb.append(",\"values\":")
                .append(Arrays.toString(values));
        sb.append('}');
        return sb.toString();
    }

    Input value;
    Input[] values;

    public static void main(String[] args) {

        System.out.println(InputHand.start);
    }


}



 
