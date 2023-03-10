package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.ManufacturerService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private InstanceContainer<Manufacturer> manufacturerDc;

    @Subscribe("calculateBtn")
    public void onCalculateBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption(messageBundle.formatMessage("countOfCarsByEngineType",
                        manufacturerService.calculateCountOfElectricCars(manufacturerDc.getItem().getId()),
                        manufacturerService.calculateCountOfGasolineCars(manufacturerDc.getItem().getId())))
                .withType(Notifications.NotificationType.WARNING)
                .show();
    }
}