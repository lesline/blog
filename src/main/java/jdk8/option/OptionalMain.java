package jdk8.option;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .map(optName -> optName.get())
                .collect(toSet());
    }


    public void getCarInsuranceName2(Optional<Insurance> insurance) {


//        //java7
//        String name = null;
//        if (insurance != null) {
//            name = insurance.getName();
//        }
//
//        //不建议这样写，跟java7区别不大
//        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
//        String name = null;
//        if (optInsurance.isPresent()) {
//            name = insurance.get().getName();
//        }
//
//        //建议用法
//        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
//        String name = optInsurance.map(Insurance::getName).orElse("");
//        String name = optInsurance.map(Insurance::getName).orElseThrow(()->new RuntimeException("姓名不能为空"));
//
//        //如果不确定空时的操作，直接返回Optional
//        Optional<String> name = optInsurance.map(Insurance::getName);

    }


}
