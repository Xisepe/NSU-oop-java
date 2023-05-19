package factory;

import factory.config.FactoryConfig;
import factory.dealer.Dealer;
import factory.delay.Delay;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Engine;
import lombok.Getter;

@Getter
public class DelayProvider {
    private final Delay bodySupplierDelay;
    private final Delay engineSupplierDelay;
    private final Delay accessorySupplierDelay;
    private final Delay dealerDelay;

    public DelayProvider(FactoryConfig config) {
        bodySupplierDelay = config.getDefaultDelay().get(Body.class.getName());
        engineSupplierDelay = config.getDefaultDelay().get(Engine.class.getName());
        accessorySupplierDelay = config.getDefaultDelay().get(Accessory.class.getName());
        dealerDelay = config.getDefaultDelay().get(Dealer.class.getName());
    }
}
