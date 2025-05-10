package org.example;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class meal {
    NewCustomer cus;
    LocalTime delievryTime;
    boolean delivered;
    public meal(NewCustomer cus, LocalTime deliveryTime, boolean delivered) {
        this.cus = cus;
        this.delievryTime=deliveryTime;
        this.delivered=false;
    }
    public void setcus(NewCustomer cus) {
        this.cus = cus;
    }
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    public NewCustomer getCus() {
        return cus;
    }
    public void setDeliveryTime(LocalTime deliveryTime) {
        this.delievryTime=deliveryTime;
    }
    public LocalTime getDeliveryTime() {
        return delievryTime;
    }
    public boolean isDelivered() {
        return delivered;
    }


}
