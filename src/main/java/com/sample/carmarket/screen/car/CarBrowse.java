package com.sample.carmarket.screen.car;

import com.sample.carmarket.entity.Status;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("carsTable")
public class CarBrowse extends StandardLookup<Car> {
    @Autowired
    private CollectionContainer<Car> carsDc;
    @Autowired
    private CollectionLoader<Car> carsDl;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private Notifications notifications;
    @Autowired
    private DataManager dataManager;

    @Subscribe("markAsSoldBtn")
    public void onMarkAsSoldBtnClick(Button.ClickEvent event) {
        Car currentCar = carsDc.getItem();
        if (currentCar.getStatus().equals(Status.SOLD)) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("notification.CarIsAlreadySold"))
                    .withType(Notifications.NotificationType.WARNING)
                    .show();
        } else {
            currentCar.setStatus(Status.SOLD);
            currentCar.setDateOfSale(LocalDate.now());
            dataManager.save(currentCar);
            notifications.create()
                    .withCaption(messageBundle.getMessage("notification.Done"))
                    .withType(Notifications.NotificationType.TRAY)
                    .show();
        }
    }
}