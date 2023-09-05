package com.lrtech.lrbackend.bootstrap;

import com.lrtech.lrbackend.models.Filament;
import com.lrtech.lrbackend.models.FilamentColor;
import com.lrtech.lrbackend.models.Product;
import com.lrtech.lrbackend.repositories.FilamentColorRepository;
import com.lrtech.lrbackend.repositories.FilamentRepository;
import com.lrtech.lrbackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final FilamentRepository filamentRepository;

    private final FilamentColorRepository filamentColorRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProductTestData();
    }

    private void loadProductTestData(){
        if(productRepository.count() == 0){
            Filament f1 = Filament.builder()
                    .type("PLA")
                    .description("Easy to print, light-weight plastic")
                    .build();
            Filament f1saved = filamentRepository.save(f1);

            Filament f2 = Filament.builder()
                    .type("PETG")
                    .description("More durable and heat resistant plastic")
                    .build();
            Filament f2saved = filamentRepository.save(f2);

            FilamentColor c1 = FilamentColor.builder()
                    .name("Magenta")
                    .availableGrams(1000)
                    .build();
           c1.setFilament(f1saved);

            FilamentColor c2 = FilamentColor.builder()
                    .name("Black")
                    .availableGrams(30)
                    .build();
            c2.setFilament(f2saved);
            filamentColorRepository.saveAll(Arrays.asList(c1, c2));



            Product p1 = Product.builder()
                    .productName("Power Bank")
                    .filaments(new HashSet<>(Arrays.asList(f1, f2)))
                    .price(BigDecimal.valueOf(49.99))
                    .filamentGrams(201)
                    .description("This is the brand new power bank with an amazing 12000 mAh capacity, and a usbC fast charge port for quickly recharging the bank. It features two usbA outputs, a usbC in/out, and a micro usb input.")
                    .imageUrl("https://picsum.photos/250/200?random=1")
                    .build();

            Product p2 = Product.builder()
                    .productName("Bluetooth Speaker")
                    .filaments(new HashSet<>(Arrays.asList(f1saved, f2saved)))
                    .price(BigDecimal.valueOf(35.50))
                    .filamentGrams(180)
                    .description("This is the brand new power bank with an amazing 12000 mAh capacity, and a usbC fast charge port for quickly recharging the bank. It features two usbA outputs, a usbC in/out, and a micro usb input.")
                    .imageUrl("https://picsum.photos/250/200?random=2")
                    .build();

            Product p3 = Product.builder()
                    .productName("Speaker Amplifier (100W)")
                    .filaments(new HashSet<>(Arrays.asList(f1saved, f2saved)))
                    .price(BigDecimal.valueOf(62.99))
                    .filamentGrams(300)
                    .description("Great 100W amp with two midrange channels and a dedicated subwoofer channel. Also equipped with a USB-A fast charging port.")
                    .imageUrl("https://picsum.photos/250/200?random=3")
                    .build();

            for (Product product : productRepository.saveAll(Arrays.asList(p1, p2, p3))) {
                System.out.println("id: " + product.getId());
            }
        }
    }
}
