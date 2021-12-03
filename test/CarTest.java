import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class CarTest {

    @Test
    public void speed_should_increase(){
        Volvo240 volvo240 = new Volvo240();
        double a = volvo240.getCurrentSpeed();
        volvo240.gas(1);
        double b = volvo240.getCurrentSpeed();
        Assertions.assertNotEquals(a,b);
    }
    @Test
    public void Saab95_turbo_should_increase_speed(){
        Saab95 car_without_turbo = new Saab95();
        Saab95 car_with_turbo = new Saab95();
        car_with_turbo.setTurboOn();
        car_with_turbo.gas(1);
        car_without_turbo.gas(1);
        Assertions.assertNotEquals(car_with_turbo.getCurrentSpeed(),car_without_turbo.getCurrentSpeed());
    }
    @Test
    public void Saab95_turbo_should_be_able_to_turn_off(){
        Saab95 car_turbo_first = new Saab95();
        Saab95 car_turbo_later = new Saab95();
        car_turbo_first.setTurboOn();
        car_turbo_first.gas(1);
        car_turbo_first.setTurboOff();
        car_turbo_first.gas(1);
        car_turbo_later.gas(1);
        car_turbo_later.setTurboOn();
        car_turbo_later.gas(1);
        Assertions.assertEquals(car_turbo_first.getCurrentSpeed(),car_turbo_later.getCurrentSpeed());
    }
    @Test
    public void setColor_and_getColor_test(){
        Volvo240 car01 = new Volvo240();
        car01.setColor(Color.cyan);
        Saab95 car02 = new Saab95();
        car02.setColor(Color.cyan);
        Assertions.assertEquals(car01.getColor(), car02.getColor());
    }
    @Test
    public void speed_should_increase_then_decrease(){
        Volvo240 car01 = new Volvo240();
        double before_gas = car01.getCurrentSpeed();
        car01.gas(1);
        car01.brake(1);
        double after_brake = car01.getCurrentSpeed();
        Assertions.assertEquals(before_gas,after_brake);
    }
    @Test
    public void Doors_should_be_add_doors(){
        Volvo240 volvo240 = new Volvo240();
        int doors = volvo240.getNrDoors() ;
        Assertions.assertEquals(doors, 4);
    }
    @Test
    public void Engine_power_should_get_power(){
        Volvo240 volvo240 = new Volvo240();
        double engine_power = volvo240.getEnginePower();
        Assertions.assertEquals(engine_power,100);
    }
    @Test
    public void Engine_should_start(){
        Volvo240 volvo240 = new Volvo240();
        volvo240.startEngine();
        double current_speed = volvo240.getCurrentSpeed();
        Assertions.assertEquals(current_speed,0.1);
    }
    @Test
    public void Engine_should_stop(){
        Volvo240 volvo240 = new Volvo240();
        volvo240.stopEngine();
        double current_speed = volvo240.getCurrentSpeed();
        Assertions.assertEquals(current_speed,0);
    }
    @Test
    public void should_move_on_Y_axis_and_X_axis(){
        Saab95 carY_axis = new Saab95();
        Saab95 carX_axis = new Saab95();
        carY_axis.gas(1);
        carY_axis.move();
        carX_axis.turnRight();
        carX_axis.gas(1);
        carX_axis.move();

        Assertions.assertEquals(carY_axis.getPositionY(),carX_axis.getPositionX());
    }
    @Test
    public void turn_functions_should_work(){
        Saab95 car_turning_left = new Saab95();
        Saab95 car_turning_right = new Saab95();
        car_turning_right.turnRight();
        car_turning_right.gas(1);
        car_turning_right.move();

        car_turning_left.turnLeft();
        car_turning_left.gas(1);
        car_turning_left.move();

        Assertions.assertEquals(car_turning_right.getPositionX(),(-1*car_turning_left.getPositionX()));
    }
    @Test
    public void invalid_gas_intervall_shouldnt_work(){
        Saab95 car01 = new Saab95();
        car01.gas(1);
        double a = car01.getCurrentSpeed();
        car01.gas(2);
        double b = car01.getCurrentSpeed();

        Assertions.assertEquals(a,b);
    }
    @Test
    public void invalid_break_intervall_shouldnt_work(){
        Saab95 car01 = new Saab95();
        double a = car01.getCurrentSpeed();
        car01.gas(1);
        car01.brake(2);
        double b = car01.getCurrentSpeed();
        car01.brake(1);
        double c = car01.getCurrentSpeed();
        Assertions.assertEquals(a,c);
        Assertions.assertNotEquals(a,b);
    }

    // Scania-tests

    @Test
    public void scania_truck_shouldnt_move_if_platform_is_raised(){
        Scania truck = new Scania();
        truck.raisePlatform(10);
        double a = truck.getCurrentSpeed();
        truck.gas(1);
        double b = truck.getCurrentSpeed();
        Assertions.assertEquals(a,b);
    }
    @Test
    public void scania_truck_platform_shouldnt_raise_in_motion(){
        Scania truck = new Scania();
        truck.gas(1);
        double a = truck.getPlatform();
        truck.raisePlatform(10);
        double b = truck.getPlatform();
        Assertions.assertEquals(a,b);
    }
    @Test
    public void scania_truck_lowering_and_raising_high_amount_should_work(){
        Scania truck = new Scania();
        truck.raisePlatform(100);
        Assertions.assertEquals(truck.getPlatform(),70);
        truck.lowerPlatform(1000);
        Assertions.assertEquals(truck.getPlatform(),0);
    }
    @Test
    public void CarTransporter_lowering_and_raising_ramp_works(){
        CarTransporter truck = new CarTransporter(10);
        boolean a = truck.getRampUp();
        truck.lowerRamp();
        Assertions.assertNotEquals(a,truck.getRampUp());
        truck.raiseRamp();
        Assertions.assertEquals(a,truck.getRampUp());
    }
    @Test
    public void CarTransporter_lowering_in_motion_shouldnt_work(){
        CarTransporter truck = new CarTransporter(10);
        truck.gas(1);
        boolean a = truck.getRampUp();
        truck.lowerRamp();
        Assertions.assertEquals(a,truck.getRampUp());
    }
    @Test
    public void CarTransporter_cant_gas_while_ramp_is_down(){
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        double a  = truck.getCurrentSpeed();
        truck.gas(1);
        Assertions.assertEquals(a,truck.getCurrentSpeed());

    }

    @Test
    public void CarTransporter_should_load_Volvo() {
        Volvo240 car = new Volvo240();
        CarTransporter truck_that_loads = new CarTransporter(10);
        truck_that_loads.lowerRamp();
        Assertions.assertDoesNotThrow(() -> truck_that_loads.loadCar(car));
    }


    @Test
    public void CarTransporter_shouldnt_load_Scania(){
        Scania scania = new Scania();
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        boolean thrown = false;
        try {
            truck.loadCar(scania);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);
   }
    @Test
    public void CarTransporter_shouldnt_load_car_when_ramp_is_up(){
        Volvo240 car = new Volvo240();
        CarTransporter truck = new CarTransporter(10);
        boolean thrown = false;
        try {
            truck.loadCar(car);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }
    @Test
    public void CarTransporter_shouldnt_load_a_car_thats_far_away(){
        Volvo240 car = new Volvo240();
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        car.gas(1);
        for (int i = 0; i < 10; i++){
         car.move();
        }
        boolean thrown = false;
        try {
            truck.loadCar(car);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }
   @Test
    public void CarTransporter_cant_unload_when_empty(){
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        Assertions.assertThrows(IllegalStateException.class, truck::unloadCar);
    }
    @Test
    public void CarTransporter_cant_unload_when_ramp_is_raised(){
        CarTransporter truck = new CarTransporter(10);
        Assertions.assertThrows(IllegalStateException.class, truck::unloadCar);
    }
   @Test
    public void Loaded_cars_should_move_with_CarTransporter(){
        CarTransporter truck = new CarTransporter(10);
        Volvo240 car = new Volvo240();
        truck.lowerRamp();
        truck.loadCar(car);
        truck.raiseRamp();
        truck.gas(1);
        truck.move();
        Assertions.assertEquals(car.getPositionX(),truck.getPositionX());
        Assertions.assertEquals(car.getPositionY(),truck.getPositionY());
   }
   @Test
    public void unload_car_should_work_as_intended(){
        Volvo240 car = new Volvo240();
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        truck.loadCar(car);
        truck.unloadCar();
        Assertions.assertEquals(car.getPositionY(),-1);
        Assertions.assertEquals(car.getPositionX(),-1);
   }
   @Test
    public void gas_shouldnt_work_when_platform_is_lowered(){
        CarTransporter truck = new CarTransporter(10);
        truck.lowerRamp();
        truck.gas(1);
        Assertions.assertEquals(0,truck.getPositionX());
        Assertions.assertEquals(0,truck.getPositionY());
   }
   @Test
    public void volvo_should_load_to_workshop(){
        CarRepairShop<Volvo240> volvoWorkShop = new CarRepairShop<>(10,1,1);
        Volvo240 car = new Volvo240();
        volvoWorkShop.loadCar(car);
        Assertions.assertEquals(1,volvoWorkShop.getAmountOfCarsInWorkshop());
   }
    @Test
    public void volvo_should_unload_from_repair_shop(){
        CarRepairShop<Volvo240> volvoRepairShop = new CarRepairShop<>(10,1,1);
        Volvo240 car = new Volvo240();
        volvoRepairShop.loadCar(car);
        volvoRepairShop.unloadCar();
        Assertions.assertEquals(0,volvoRepairShop.getAmountOfCarsInWorkshop());
    }
    @Test
    public void unloading_shouldnt_work_if_already_empty_workshop(){
        CarRepairShop<Car> repairShop = new CarRepairShop<>(10,1,1);
        Assertions.assertThrows(IllegalStateException.class, repairShop::unloadCar);
    }
    @Test
    public void shouldnt_load_car_thats_too_far_away_from_workshop(){
        CarRepairShop<Car> repairShop = new CarRepairShop<>(10,0,0);
        Volvo240 car = new Volvo240();
        car.gas(1);
        for (int i = 0; i < 10; i++){
            car.move();
        }
        boolean thrown = false;
        try {
            repairShop.loadCar(car);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }
    @Test
    public void shouldnt_load_car_when_workshop_is_full(){
        CarRepairShop<Car> repairShop = new CarRepairShop<>(1,0,0);
        Volvo240 car = new Volvo240();
        repairShop.loadCar(car);
        Volvo240 car2 = new Volvo240();
        boolean thrown = false;
        try {
            repairShop.loadCar(car2);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }
    @Test
    public void car_transporter_shouldnt_load_when_full(){
       CarTransporter truck = new CarTransporter(1);
       Volvo240 car1 = new Volvo240();
       Volvo240 car2 = new Volvo240();
       truck.lowerRamp();
       truck.loadCar(car1);


        boolean thrown = false;
        try {
            truck.loadCar(car2);
        }
        catch (IllegalStateException e){
            thrown = true;
        }
        Assertions.assertTrue(thrown);

    }
}
