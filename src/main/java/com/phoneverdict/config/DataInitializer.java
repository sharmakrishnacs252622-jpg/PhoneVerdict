package com.phoneverdict.config;

import com.phoneverdict.model.Phone;
import com.phoneverdict.model.Review;
import com.phoneverdict.model.ReviewStatus;
import com.phoneverdict.model.Role;
import com.phoneverdict.model.User;
import com.phoneverdict.repository.PhoneRepository;
import com.phoneverdict.repository.ReviewRepository;
import com.phoneverdict.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository,
                           PhoneRepository phoneRepository,
                           ReviewRepository reviewRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.reviewRepository = reviewRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        // 1. Users
        User admin = new User();
        admin.setName("Admin");
        admin.setEmail("admin@phoneverdict.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        User user1 = new User();
        user1.setName("Rahul Sharma");
        user1.setEmail("user@phoneverdict.com");
        user1.setPassword(passwordEncoder.encode("user123"));
        user1.setRole(Role.USER);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Priya Patel");
        user2.setEmail("priya@phoneverdict.com");
        user2.setPassword(passwordEncoder.encode("user123"));
        user2.setRole(Role.USER);
        userRepository.save(user2);

        User user3 = new User();
        user3.setName("Amit Kumar");
        user3.setEmail("amit@phoneverdict.com");
        user3.setPassword(passwordEncoder.encode("user123"));
        user3.setRole(Role.USER);
        userRepository.save(user3);

        // 2. Phones
        // Phone 1: Samsung Galaxy S25 Ultra
        Phone p1 = new Phone();
        p1.setBrand("Samsung");
        p1.setModel("Galaxy S25 Ultra");
        p1.setPrice(134999.0);
        p1.setImageUrl("https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=500&auto=format&fit=crop&q=80");
        p1.setReleaseDate(LocalDate.of(2025, 1, 22));
        p1.setDisplaySize("6.9 inches");
        p1.setResolution("3120 x 1440");
        p1.setRefreshRate("120Hz");
        p1.setPanelType("Dynamic AMOLED 2X");
        p1.setProcessor("Snapdragon 8 Elite");
        p1.setRam("12 GB");
        p1.setStorage("256 GB");
        p1.setGpu("Adreno 830");
        p1.setMainCamera("200 MP");
        p1.setUltrawideCamera("50 MP");
        p1.setTelephotoCamera("50 MP (5x Optical)");
        p1.setFrontCamera("12 MP");
        p1.setBattery("5000 mAh");
        p1.setChargingSpeed("45W Wired, 15W Wireless");
        p1.setOs("Android 15 (One UI 7)");
        p1.setFiveG(true);
        p1.setWifi("Wi-Fi 7");
        p1.setBluetooth("5.4");
        p1.setNfc(true);
        p1.setWaterResistance("IP68");
        p1.setWeight("218g");
        p1.setColors("Titanium Black, Titanium Gray, Titanium Silver, Titanium Blue");
        p1.setPros("Industry-leading zoom camera;Integrated S-Pen;Stunning anti-reflective display;Exceptional battery endurance");
        p1.setCons("Very expensive;Chunky form factor;Charging speed lags behind Chinese flagships");
        p1.setPerformanceScore(9.7);
        p1.setCameraScore(9.6);
        p1.setBatteryScore(8.8);
        p1.setDisplayScore(9.8);
        p1.setValueScore(7.6);
        phoneRepository.save(p1);

        // Phone 2: Apple iPhone 16 Pro Max
        Phone p2 = new Phone();
        p2.setBrand("Apple");
        p2.setModel("iPhone 16 Pro Max");
        p2.setPrice(144900.0);
        p2.setImageUrl("https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=500&auto=format&fit=crop&q=80");
        p2.setReleaseDate(LocalDate.of(2024, 9, 20));
        p2.setDisplaySize("6.9 inches");
        p2.setResolution("2868 x 1320");
        p2.setRefreshRate("120Hz");
        p2.setPanelType("Super Retina XDR OLED");
        p2.setProcessor("A18 Pro");
        p2.setRam("8 GB");
        p2.setStorage("256 GB");
        p2.setGpu("Apple 6-Core GPU");
        p2.setMainCamera("48 MP Fusion");
        p2.setUltrawideCamera("48 MP");
        p2.setTelephotoCamera("12 MP 5x Tetraprism");
        p2.setFrontCamera("12 MP TrueDepth");
        p2.setBattery("4685 mAh");
        p2.setChargingSpeed("27W Wired, 25W MagSafe");
        p2.setOs("iOS 18");
        p2.setFiveG(true);
        p2.setWifi("Wi-Fi 7");
        p2.setBluetooth("5.3");
        p2.setNfc(true);
        p2.setWaterResistance("IP68");
        p2.setWeight("227g");
        p2.setColors("Desert Titanium, Natural Titanium, White Titanium, Black Titanium");
        p2.setPros("Unbeatable 4K 120fps video recording;Class-leading battery life;Dedicated Camera Control button;Premium Titanium build");
        p2.setCons("Astronomical price;Slow charging speeds;Apple Intelligence rolled out gradually");
        p2.setPerformanceScore(9.8);
        p2.setCameraScore(9.5);
        p2.setBatteryScore(9.2);
        p2.setDisplayScore(9.6);
        p2.setValueScore(7.2);
        phoneRepository.save(p2);

        // Phone 3: OnePlus 13
        Phone p3 = new Phone();
        p3.setBrand("OnePlus");
        p3.setModel("13");
        p3.setPrice(69999.0);
        p3.setImageUrl("https://images.unsplash.com/photo-1580910051074-3eb694886505?w=500&auto=format&fit=crop&q=80");
        p3.setReleaseDate(LocalDate.of(2025, 1, 7));
        p3.setDisplaySize("6.82 inches");
        p3.setResolution("3168 x 1440");
        p3.setRefreshRate("120Hz");
        p3.setPanelType("BOE X2 2K LTPO AMOLED");
        p3.setProcessor("Snapdragon 8 Elite");
        p3.setRam("12 GB");
        p3.setStorage("256 GB");
        p3.setGpu("Adreno 830");
        p3.setMainCamera("50 MP Sony LYT-808");
        p3.setUltrawideCamera("50 MP");
        p3.setTelephotoCamera("50 MP Periscope 3x");
        p3.setFrontCamera("32 MP");
        p3.setBattery("6000 mAh Glacier");
        p3.setChargingSpeed("100W Wired, 50W Wireless");
        p3.setOs("Android 15 (OxygenOS 15)");
        p3.setFiveG(true);
        p3.setWifi("Wi-Fi 7");
        p3.setBluetooth("5.4");
        p3.setNfc(true);
        p3.setWaterResistance("IP69 & IP68");
        p3.setWeight("210g");
        p3.setColors("Black Eclipse, Arctic Dawn, Midnight Ocean");
        p3.setPros("Massive 6000mAh battery with blazing 100W charging;Flagship Snapdragon 8 Elite at aggressive price;IP69 water jet resistance;Clean OxygenOS 15");
        p3.setCons("Cameras slightly behind S25 Ultra in zoom;No charger in some global regions");
        p3.setPerformanceScore(9.6);
        p3.setCameraScore(8.9);
        p3.setBatteryScore(9.8);
        p3.setDisplayScore(9.4);
        p3.setValueScore(9.4);
        phoneRepository.save(p3);

        // Phone 4: Google Pixel 9 Pro
        Phone p4 = new Phone();
        p4.setBrand("Google");
        p4.setModel("Pixel 9 Pro");
        p4.setPrice(109999.0);
        p4.setImageUrl("https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=500&auto=format&fit=crop&q=80");
        p4.setReleaseDate(LocalDate.of(2024, 8, 22));
        p4.setDisplaySize("6.3 inches");
        p4.setResolution("2856 x 1280");
        p4.setRefreshRate("120Hz");
        p4.setPanelType("Super Actua OLED");
        p4.setProcessor("Google Tensor G4");
        p4.setRam("16 GB");
        p4.setStorage("128 GB");
        p4.setGpu("Mali-G715");
        p4.setMainCamera("50 MP");
        p4.setUltrawideCamera("48 MP");
        p4.setTelephotoCamera("48 MP 5x");
        p4.setFrontCamera("42 MP");
        p4.setBattery("4700 mAh");
        p4.setChargingSpeed("27W Wired, 21W Wireless");
        p4.setOs("Android 15");
        p4.setFiveG(true);
        p4.setWifi("Wi-Fi 7");
        p4.setBluetooth("5.3");
        p4.setNfc(true);
        p4.setWaterResistance("IP68");
        p4.setWeight("199g");
        p4.setColors("Obsidian, Porcelain, Hazel, Rose Quartz");
        p4.setPros("Unrivaled computational photography;7 years of OS updates;Compact flagship ergonomics;Top-tier AI features");
        p4.setCons("Tensor G4 raw benchmark gaming throttles;Slow wired charging;Base model starts at 128GB");
        p4.setPerformanceScore(8.6);
        p4.setCameraScore(9.7);
        p4.setBatteryScore(8.0);
        p4.setDisplayScore(9.5);
        p4.setValueScore(7.8);
        phoneRepository.save(p4);

        // Phone 5: Xiaomi 14 Pro
        Phone p5 = new Phone();
        p5.setBrand("Xiaomi");
        p5.setModel("14 Pro");
        p5.setPrice(54999.0);
        p5.setImageUrl("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=500&auto=format&fit=crop&q=80");
        p5.setReleaseDate(LocalDate.of(2024, 2, 25));
        p5.setDisplaySize("6.73 inches");
        p5.setResolution("3200 x 1440");
        p5.setRefreshRate("120Hz");
        p5.setPanelType("LTPO AMOLED");
        p5.setProcessor("Snapdragon 8 Gen 3");
        p5.setRam("12 GB");
        p5.setStorage("256 GB");
        p5.setGpu("Adreno 750");
        p5.setMainCamera("50 MP Leica Summilux");
        p5.setUltrawideCamera("50 MP");
        p5.setTelephotoCamera("50 MP Floating Telephoto");
        p5.setFrontCamera("32 MP");
        p5.setBattery("4880 mAh");
        p5.setChargingSpeed("120W HyperCharge, 50W Wireless");
        p5.setOs("Android 14 (HyperOS)");
        p5.setFiveG(true);
        p5.setWifi("Wi-Fi 7");
        p5.setBluetooth("5.4");
        p5.setNfc(true);
        p5.setWaterResistance("IP68");
        p5.setWeight("223g");
        p5.setColors("Black, Silver, Jade Green, Titanium");
        p5.setPros("Sublime Leica photographic color science;Incredible 120W charging (full in 18 mins);Ultra bright 3000 nits display");
        p5.setCons("HyperOS bloatware notifications;Selfie camera limited in low light;Heavier than competitors");
        p5.setPerformanceScore(9.1);
        p5.setCameraScore(9.3);
        p5.setBatteryScore(8.6);
        p5.setDisplayScore(9.2);
        p5.setValueScore(9.1);
        phoneRepository.save(p5);

        // Phone 6: Vivo X200 Pro
        Phone p6 = new Phone();
        p6.setBrand("Vivo");
        p6.setModel("X200 Pro");
        p6.setPrice(94999.0);
        p6.setImageUrl("https://images.unsplash.com/photo-1565849904461-04a58ad377e0?w=500&auto=format&fit=crop&q=80");
        p6.setReleaseDate(LocalDate.of(2024, 12, 12));
        p6.setDisplaySize("6.78 inches");
        p6.setResolution("2800 x 1260");
        p6.setRefreshRate("120Hz");
        p6.setPanelType("LTPO AMOLED");
        p6.setProcessor("MediaTek Dimensity 9400");
        p6.setRam("16 GB");
        p6.setStorage("512 GB");
        p6.setGpu("Immortalis-G925");
        p6.setMainCamera("50 MP Sony LYT-818 ZEISS");
        p6.setUltrawideCamera("50 MP");
        p6.setTelephotoCamera("200 MP ZEISS APO Telephoto");
        p6.setFrontCamera("32 MP");
        p6.setBattery("6000 mAh BlueOcean");
        p6.setChargingSpeed("90W FlashCharge, 30W Wireless");
        p6.setOs("Android 15 (Funtouch OS 15)");
        p6.setFiveG(true);
        p6.setWifi("Wi-Fi 7");
        p6.setBluetooth("5.4");
        p6.setNfc(true);
        p6.setWaterResistance("IP69 & IP68");
        p6.setWeight("228g");
        p6.setColors("Titanium Gray, Moonlight White, Cosmos Black");
        p6.setPros("Astronomical 200MP telephoto camera performance;Massive 6000mAh battery life;Dimensity 9400 extreme efficiency;ZEISS T* anti-glare coating");
        p6.setCons("Noticeable camera bump;Heavy in hand;Funtouch OS UI design quirks");
        p6.setPerformanceScore(9.5);
        p6.setCameraScore(9.8);
        p6.setBatteryScore(9.6);
        p6.setDisplayScore(9.3);
        p6.setValueScore(8.4);
        phoneRepository.save(p6);

        // Phone 7: Realme GT 6
        Phone p7 = new Phone();
        p7.setBrand("Realme");
        p7.setModel("GT 6");
        p7.setPrice(37999.0);
        p7.setImageUrl("https://images.unsplash.com/photo-1574944985070-8f3ebc6b79d2?w=500&auto=format&fit=crop&q=80");
        p7.setReleaseDate(LocalDate.of(2024, 6, 20));
        p7.setDisplaySize("6.78 inches");
        p7.setResolution("2780 x 1264");
        p7.setRefreshRate("120Hz");
        p7.setPanelType("8T LTPO AMOLED 6000 nits");
        p7.setProcessor("Snapdragon 8s Gen 3");
        p7.setRam("12 GB");
        p7.setStorage("256 GB");
        p7.setGpu("Adreno 735");
        p7.setMainCamera("50 MP Sony LYT-808 OIS");
        p7.setUltrawideCamera("8 MP");
        p7.setTelephotoCamera("50 MP 2x Telephoto");
        p7.setFrontCamera("32 MP");
        p7.setBattery("5500 mAh");
        p7.setChargingSpeed("120W SUPERVOOC");
        p7.setOs("Android 14 (Realme UI 5)");
        p7.setFiveG(true);
        p7.setWifi("Wi-Fi 6");
        p7.setBluetooth("5.4");
        p7.setNfc(true);
        p7.setWaterResistance("IP65");
        p7.setWeight("199g");
        p7.setColors("Fluid Silver, Razor Green");
        p7.setPros("Phenomenal value for price-to-performance;6000 nits peak display brightness;Blistering 120W fast charging;Dedicated 50MP portrait telephoto");
        p7.setCons("Weak 8MP ultrawide sensor;No wireless charging;Plastic side rails");
        p7.setPerformanceScore(8.8);
        p7.setCameraScore(8.2);
        p7.setBatteryScore(9.2);
        p7.setDisplayScore(9.4);
        p7.setValueScore(9.6);
        phoneRepository.save(p7);

        // Phone 8: Samsung Galaxy A55
        Phone p8 = new Phone();
        p8.setBrand("Samsung");
        p8.setModel("Galaxy A55");
        p8.setPrice(39999.0);
        p8.setImageUrl("https://images.unsplash.com/photo-1546868871-7041f2a55e12?w=500&auto=format&fit=crop&q=80");
        p8.setReleaseDate(LocalDate.of(2024, 3, 11));
        p8.setDisplaySize("6.6 inches");
        p8.setResolution("2340 x 1080");
        p8.setRefreshRate("120Hz");
        p8.setPanelType("Super AMOLED");
        p8.setProcessor("Exynos 1480 (AMD GPU)");
        p8.setRam("8 GB");
        p8.setStorage("128 GB");
        p8.setGpu("Xclipse 530");
        p8.setMainCamera("50 MP OIS");
        p8.setUltrawideCamera("12 MP");
        p8.setTelephotoCamera("5 MP Macro");
        p8.setFrontCamera("32 MP");
        p8.setBattery("5000 mAh");
        p8.setChargingSpeed("25W Wired");
        p8.setOs("Android 14 (One UI 6.1)");
        p8.setFiveG(true);
        p8.setWifi("Wi-Fi 6");
        p8.setBluetooth("5.3");
        p8.setNfc(true);
        p8.setWaterResistance("IP67");
        p8.setWeight("213g");
        p8.setColors("Awesome Iceblue, Awesome Lilac, Awesome Lemon, Awesome Navy");
        p8.setPros("Premium metal frame & glass design;Guaranteed 4 OS updates;Solid IP67 water protection;Vibrant Super AMOLED screen");
        p8.setCons("Sluggish 25W charging without in-box charger;Thick bezels;Exynos processor trails gaming rivals");
        p8.setPerformanceScore(7.5);
        p8.setCameraScore(7.8);
        p8.setBatteryScore(8.4);
        p8.setDisplayScore(8.2);
        p8.setValueScore(8.1);
        phoneRepository.save(p8);

        // Phone 9: Motorola Edge 50 Pro
        Phone p9 = new Phone();
        p9.setBrand("Motorola");
        p9.setModel("Edge 50 Pro");
        p9.setPrice(31999.0);
        p9.setImageUrl("https://images.unsplash.com/photo-1567581935884-3349723552ca?w=500&auto=format&fit=crop&q=80");
        p9.setReleaseDate(LocalDate.of(2024, 4, 2));
        p9.setDisplaySize("6.7 inches");
        p9.setResolution("2712 x 1220");
        p9.setRefreshRate("144Hz");
        p9.setPanelType("1.5K pOLED Pantone Validated");
        p9.setProcessor("Snapdragon 7 Gen 3");
        p9.setRam("8 GB");
        p9.setStorage("256 GB");
        p9.setGpu("Adreno 720");
        p9.setMainCamera("50 MP OIS Pantone");
        p9.setUltrawideCamera("13 MP (Macro)");
        p9.setTelephotoCamera("10 MP 3x OIS");
        p9.setFrontCamera("50 MP with AF");
        p9.setBattery("4500 mAh");
        p9.setChargingSpeed("125W TurboPower, 50W Wireless");
        p9.setOs("Android 14 (Hello UI)");
        p9.setFiveG(true);
        p9.setWifi("Wi-Fi 6E");
        p9.setBluetooth("5.4");
        p9.setNfc(true);
        p9.setWaterResistance("IP68");
        p9.setWeight("186g");
        p9.setColors("Luxe Lavender, Moonlight Pearl, Black Beauty");
        p9.setPros("Stunning 144Hz curved screen;125W wired + 50W wireless charging;Lightweight vegan leather body;50MP autofocus selfie camera");
        p9.setCons("4500mAh battery smaller than modern standard;Occasional camera shutter lag;Only 3 OS updates guaranteed");
        p9.setPerformanceScore(8.0);
        p9.setCameraScore(8.4);
        p9.setBatteryScore(7.5);
        p9.setDisplayScore(9.0);
        p9.setValueScore(9.2);
        phoneRepository.save(p9);

        // Phone 10: OnePlus Nord 4
        Phone p10 = new Phone();
        p10.setBrand("OnePlus");
        p10.setModel("Nord 4");
        p10.setPrice(29999.0);
        p10.setImageUrl("https://images.unsplash.com/photo-1512499617640-c74ae3a79d37?w=500&auto=format&fit=crop&q=80");
        p10.setReleaseDate(LocalDate.of(2024, 7, 16));
        p10.setDisplaySize("6.74 inches");
        p10.setResolution("2772 x 1240");
        p10.setRefreshRate("120Hz");
        p10.setPanelType("AMOLED 1.5K");
        p10.setProcessor("Snapdragon 7+ Gen 3");
        p10.setRam("8 GB");
        p10.setStorage("128 GB");
        p10.setGpu("Adreno 732");
        p10.setMainCamera("50 MP Sony LYT-600 OIS");
        p10.setUltrawideCamera("8 MP");
        p10.setTelephotoCamera("N/A");
        p10.setFrontCamera("16 MP");
        p10.setBattery("5500 mAh");
        p10.setChargingSpeed("100W SUPERVOOC");
        p10.setOs("Android 14 (OxygenOS 14.1)");
        p10.setFiveG(true);
        p10.setWifi("Wi-Fi 6");
        p10.setBluetooth("5.4");
        p10.setNfc(true);
        p10.setWaterResistance("IP65");
        p10.setWeight("199g");
        p10.setColors("Mercurial Silver, Oasis Green, Obsidian Midnight");
        p10.setPros("Sleek all-metal unibody craftsmanship;Great battery life with 100W charging;4 Android OS & 6 years security updates;High performance 7+ Gen 3");
        p10.setCons("Average 8MP ultrawide;No wireless charging;Metal body can get warm under intense gaming");
        p10.setPerformanceScore(8.5);
        p10.setCameraScore(7.8);
        p10.setBatteryScore(9.2);
        p10.setDisplayScore(8.6);
        p10.setValueScore(9.5);
        phoneRepository.save(p10);

        // Phone 11: Apple iPhone 16
        Phone p11 = new Phone();
        p11.setBrand("Apple");
        p11.setModel("iPhone 16");
        p11.setPrice(79900.0);
        p11.setImageUrl("https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?w=500&auto=format&fit=crop&q=80");
        p11.setReleaseDate(LocalDate.of(2024, 9, 20));
        p11.setDisplaySize("6.1 inches");
        p11.setResolution("2556 x 1179");
        p11.setRefreshRate("60Hz");
        p11.setPanelType("Super Retina XDR OLED");
        p11.setProcessor("A18");
        p11.setRam("8 GB");
        p11.setStorage("128 GB");
        p11.setGpu("Apple 5-Core GPU");
        p11.setMainCamera("48 MP Fusion");
        p11.setUltrawideCamera("12 MP Macro");
        p11.setTelephotoCamera("N/A (2x Sensor Crop)");
        p11.setFrontCamera("12 MP TrueDepth");
        p11.setBattery("3561 mAh");
        p11.setChargingSpeed("20W Wired, 25W MagSafe");
        p11.setOs("iOS 18");
        p11.setFiveG(true);
        p11.setWifi("Wi-Fi 7");
        p11.setBluetooth("5.3");
        p11.setNfc(true);
        p11.setWaterResistance("IP68");
        p11.setWeight("170g");
        p11.setColors("Ultramarine, Teal, Pink, White, Black");
        p11.setPros("Action Button & Camera Control button included;Apple Intelligence ready;Compact and featherweight;Great main camera photo quality");
        p11.setCons("Still restricted to 60Hz refresh rate;Slow 20W wired charging;128GB starting storage");
        p11.setPerformanceScore(9.3);
        p11.setCameraScore(8.7);
        p11.setBatteryScore(7.6);
        p11.setDisplayScore(7.5);
        p11.setValueScore(7.8);
        phoneRepository.save(p11);

        // Phone 12: Xiaomi Redmi Note 13 Pro+
        Phone p12 = new Phone();
        p12.setBrand("Xiaomi");
        p12.setModel("Redmi Note 13 Pro+");
        p12.setPrice(31999.0);
        p12.setImageUrl("https://images.unsplash.com/photo-1591337676887-a217a6970a8a?w=500&auto=format&fit=crop&q=80");
        p12.setReleaseDate(LocalDate.of(2024, 1, 15));
        p12.setDisplaySize("6.67 inches");
        p12.setResolution("2712 x 1220");
        p12.setRefreshRate("120Hz");
        p12.setPanelType("Curved CrystalRes AMOLED");
        p12.setProcessor("MediaTek Dimensity 7200 Ultra");
        p12.setRam("8 GB");
        p12.setStorage("256 GB");
        p12.setGpu("Mali-G610 MC4");
        p12.setMainCamera("200 MP Samsung ISOCELL HP3 OIS");
        p12.setUltrawideCamera("8 MP");
        p12.setTelephotoCamera("2 MP Macro");
        p12.setFrontCamera("16 MP");
        p12.setBattery("5000 mAh");
        p12.setChargingSpeed("120W HyperCharge");
        p12.setOs("Android 14 (HyperOS)");
        p12.setFiveG(true);
        p12.setWifi("Wi-Fi 6");
        p12.setBluetooth("5.3");
        p12.setNfc(true);
        p12.setWaterResistance("IP68");
        p12.setWeight("204g");
        p12.setColors("Fusion Purple, Fusion Black, Fusion White");
        p12.setPros("High resolution 200MP camera;120W fast charging in box;Full IP68 water & dust resistance;Curved 1.5K AMOLED display");
        p12.setCons("Auxiliary cameras (8MP+2MP) are mediocre;Curved screen prone to accidental touches;Pre-installed bloatware apps");
        p12.setPerformanceScore(7.8);
        p12.setCameraScore(8.3);
        p12.setBatteryScore(8.5);
        p12.setDisplayScore(8.9);
        p12.setValueScore(9.3);
        phoneRepository.save(p12);

        // 3. Realistic Reviews
        // Reviews for Samsung Galaxy S25 Ultra
        Review r1 = new Review();
        r1.setPhone(p1);
        r1.setUser(user1);
        r1.setRating(5);
        r1.setTitle("The Pinnacle of Android Flagships!");
        r1.setComment("The S25 Ultra is worth every rupee. The Snapdragon 8 Elite keeps thermals completely in check even during 2-hour Genshin Impact sessions. The anti-reflective screen coating is genuinely life-changing outdoors.");
        r1.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r1);

        Review r2 = new Review();
        r2.setPhone(p1);
        r2.setUser(user2);
        r2.setRating(4);
        r2.setTitle("Unrivaled Camera Zoom & S-Pen");
        r2.setComment("As an architect, the S-Pen and the 5x optical telephoto are invaluable daily tools. The only drawback is the 45W charging which takes over an hour compared to 20 mins on OnePlus.");
        r2.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r2);

        // Reviews for iPhone 16 Pro Max
        Review r3 = new Review();
        r3.setPhone(p2);
        r3.setUser(user3);
        r3.setRating(5);
        r3.setTitle("Best Videography Smartphone on Earth");
        r3.setComment("The 4K 120fps Dolby Vision video recording is indistinguishable from dedicated mirrorless cameras. Battery lasts nearly 2 full days of moderate use. Camera Control button has a nice mechanical haptic feel.");
        r3.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r3);

        Review r4 = new Review();
        r4.setPhone(p2);
        r4.setUser(user1);
        r4.setRating(4);
        r4.setTitle("Exceptional Build, Hefty Price");
        r4.setComment("Superb display quality and iOS 18 feels rock solid. However, the price in India is astronomical, and Siri Apple Intelligence features took time to arrive.");
        r4.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r4);

        // Reviews for OnePlus 13
        Review r5 = new Review();
        r5.setPhone(p3);
        r5.setUser(user2);
        r5.setRating(5);
        r5.setTitle("Value Flagship King of 2025");
        r5.setComment("OnePlus is back to its glory days. 6000mAh battery paired with 100W charging is the ultimate combination. The BOE X2 display is crystal sharp and OxygenOS 15 is super smooth.");
        r5.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r5);

        // Reviews for Google Pixel 9 Pro
        Review r6 = new Review();
        r6.setPhone(p4);
        r6.setUser(user3);
        r6.setRating(5);
        r6.setTitle("Still the Photography Benchmark");
        r6.setComment("Pixel computational photography remains unmatched for skin tones and motion blur cancellation. Plus, the compact 6.3-inch size fits comfortably in one hand.");
        r6.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r6);

        // Reviews for Realme GT 6
        Review r7 = new Review();
        r7.setPhone(p7);
        r7.setUser(user1);
        r7.setRating(5);
        r7.setTitle("Insane Performance Under 40K");
        r7.setComment("Easily the most capable phone under 40,000 INR. The 8s Gen 3 processor handles multitasking effortlessly and the 120W charger in the box is great.");
        r7.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r7);

        // Reviews for Moto Edge 50 Pro
        Review r8 = new Review();
        r8.setPhone(p9);
        r8.setUser(user2);
        r8.setRating(4);
        r8.setTitle("Beautiful Design and Clean Hello UI");
        r8.setComment("The curved 144Hz screen looks premium and the vegan leather back feels great without a case. Charging at 125W is insanely fast.");
        r8.setStatus(ReviewStatus.APPROVED);
        reviewRepository.save(r8);

        // Pending review for Admin moderation demo
        Review r9 = new Review();
        r9.setPhone(p5);
        r9.setUser(user1);
        r9.setRating(5);
        r9.setTitle("Leica Optics Are Magic");
        r9.setComment("The bokeh on portraits with the 75mm floating telephoto lens looks like a DSLR. Absolutely recommend for street photographers.");
        r9.setStatus(ReviewStatus.PENDING);
        reviewRepository.save(r9);

        Review r10 = new Review();
        r10.setPhone(p10);
        r10.setUser(user3);
        r10.setRating(4);
        r10.setTitle("The Metal Back is Back!");
        r10.setComment("Love the return of metallic unibody design on the Nord 4. Battery easily lasts 1.5 days. Very solid device for 30k.");
        r10.setStatus(ReviewStatus.PENDING);
        reviewRepository.save(r10);

        System.out.println("==================================================");
        System.out.println(" PhoneVerdict: Sample Data Initialized Successfully");
        System.out.println(" Admin: admin@phoneverdict.com / admin123");
        System.out.println(" User:  user@phoneverdict.com  / user123");
        System.out.println("==================================================");
    }
}
