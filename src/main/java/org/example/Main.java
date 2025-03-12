package org.example;

import io.ebean.Database;
import io.ebean.DB;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    private static final Database database = DB.getDefault();

    public static void main(String[] args) {
        // Tạo danh sách các ChannelPrice
        List<ChannelPrice> prices = createPrices();
        
        // Tạo danh sách các SocialChannel
        List<SocialChannel> channels = createChannels(prices);
        
        // Lưu tất cả vào database
        saveData(prices, channels);
    }

    private static List<ChannelPrice> createPrices() {
        List<ChannelPrice> prices = new ArrayList<>();
        
        ChannelPrice price1 = new ChannelPrice();
        price1.setPriceName("Basic Package");
        price1.setPriceValue(100000);
        prices.add(price1);

        ChannelPrice price2 = new ChannelPrice();
        price2.setPriceName("Premium Package");
        price2.setPriceValue(200000);
        prices.add(price2);

        ChannelPrice price3 = new ChannelPrice();
        price3.setPriceName("Enterprise Package");
        price3.setPriceValue(500000);
        prices.add(price3);

        return prices;
    }

    private static List<SocialChannel> createChannels(List<ChannelPrice> prices) {
        List<SocialChannel> channels = new ArrayList<>();

        // Channel 1 với tất cả các gói giá
        SocialChannel channel1 = new SocialChannel();
        channel1.setChannelName("Facebook Channel");
        channel1.setLocation("Vietnam");
        channel1.setCreatedAt(LocalDateTime.now());
        channel1.setUpdatedAt(LocalDateTime.now());
        channel1.setChannelPrices(prices);
        channels.add(channel1);

        // Channel 2 chỉ với gói basic và premium
        SocialChannel channel2 = new SocialChannel();
        channel2.setChannelName("Instagram Channel");
        channel2.setLocation("Vietnam");
        channel2.setCreatedAt(LocalDateTime.now());
        channel2.setUpdatedAt(LocalDateTime.now());
        channel2.setChannelPrices(Arrays.asList(prices.get(0), prices.get(1)));
        channels.add(channel2);

        return channels;
    }

    private static void saveData(List<ChannelPrice> prices, List<SocialChannel> channels) {
        // Lưu tất cả prices trước
        for (ChannelPrice price : prices) {
            DB.save(price);
        }

        // Sau đó lưu các channel
        for (SocialChannel channel : channels) {
            DB.save(channel);
        }
    }
}
