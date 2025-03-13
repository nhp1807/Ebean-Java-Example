package org.example;

import io.ebean.DB;
import io.ebean.ExpressionList;
import org.example.channel.ChannelPrice;
import org.example.channel.SocialChannel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Query {
    public static void main(String[] args) {
//        setupChannel(); // Khởi tạo dữ liệu ban đầu
//        printAllChannels(); // In ra danh sách các kênh và gói giá
//        updateChannelPrice("Premium Package", 250000); // Cập nhật giá gói
//        deleteChannel("Facebook Channel"); // Xóa kênh

        List<SocialChannel> channels = findChannels(Optional.empty(), Optional.of("Global"), Optional.empty(), Optional.empty());
        channels.forEach(channel -> System.out.println("Channel: " + channel.getChannelName() + ", Location: " + channel.getLocation()));
    }

    public static void setupChannel() {
        List<ChannelPrice> prices = createPrices();
        List<SocialChannel> channels = createMoreChannels(prices);
        saveData(prices, channels);
    }

    /**
     * Tìm kiếm các SocialChannel dựa trên các điều kiện tùy chọn.
     * @param channelName Tên kênh tùy chọn để tìm kiếm.
     * @param location Vị trí tùy chọn để tìm kiếm.
     * @param createdAfter Ngày tạo kênh sau ngày này.
     * @param createdBefore Ngày tạo kênh trước ngày này.
     * @return Danh sách các kênh phù hợp với điều kiện.
     */
    public static List<SocialChannel> findChannels(Optional<String> channelName, Optional<String> location,
                                                   Optional<LocalDateTime> createdAfter, Optional<LocalDateTime> createdBefore) {
        ExpressionList<SocialChannel> query = DB.find(SocialChannel.class).where();

        channelName.ifPresent(name -> query.eq("channelName", name));
        location.ifPresent(loc -> query.eq("location", loc));
        createdAfter.ifPresent(date -> query.ge("createdAt", date));
        createdBefore.ifPresent(date -> query.le("createdAt", date));

        return query.findList();
    }

    private static List<ChannelPrice> createPrices() {
        ChannelPrice price1 = new ChannelPrice();
        price1.setPriceName("Basic Package");
        price1.setPriceValue(100000);

        ChannelPrice price2 = new ChannelPrice();
        price2.setPriceName("Premium Package");
        price2.setPriceValue(200000);

        ChannelPrice price3 = new ChannelPrice();
        price3.setPriceName("Enterprise Package");
        price3.setPriceValue(500000);

        return Arrays.asList(price1, price2, price3);
    }

    private static List<SocialChannel> createChannels(List<ChannelPrice> prices) {
        SocialChannel channel1 = new SocialChannel();
        channel1.setChannelName("Facebook Channel");
        channel1.setLocation("Vietnam");
        channel1.setCreatedAt(LocalDateTime.now());
        channel1.setUpdatedAt(LocalDateTime.now());
        channel1.setChannelPrices(prices);

        SocialChannel channel2 = new SocialChannel();
        channel2.setChannelName("Instagram Channel");
        channel2.setLocation("Vietnam");
        channel2.setCreatedAt(LocalDateTime.now());
        channel2.setUpdatedAt(LocalDateTime.now());
        channel2.setChannelPrices(Arrays.asList(prices.get(0), prices.get(1)));

        return Arrays.asList(channel1, channel2);
    }

    public static void printAllChannels() {
        List<SocialChannel> channels = DB.find(SocialChannel.class).findList();
        channels.forEach(channel -> {
            System.out.println("Channel: " + channel.getChannelName() + ", Location: " + channel.getLocation());
            channel.getChannelPrices().forEach(price ->
                    System.out.println("  Price Name: " + price.getPriceName() + ", Value: " + price.getPriceValue() + " " + price.getUnit())
            );
        });
    }

    public static void updateChannelPrice(String priceName, double newPrice) {
        ChannelPrice price = DB.find(ChannelPrice.class)
                .where()
                .eq("priceName", priceName)
                .findOne();
        if (price != null) {
            price.setPriceValue((int) newPrice);
            DB.save(price);
            System.out.println("Updated " + priceName + " to " + newPrice);
        }
    }

    public static void deleteChannel(String channelName) {
        SocialChannel channel = DB.find(SocialChannel.class)
                .where()
                .eq("channelName", channelName)
                .findOne();
        if (channel != null) {
            DB.delete(channel);
            System.out.println("Deleted " + channelName);
        } else {
            System.out.println("Channel not found: " + channelName);
        }
    }


    private static List<SocialChannel> createMoreChannels(List<ChannelPrice> prices) {
        List<SocialChannel> channels = new ArrayList<>();
        String[] channelNames = {"Twitter Channel", "LinkedIn Channel", "YouTube Channel", "Pinterest Channel", "Snapchat Channel",
                "TikTok Channel", "Reddit Channel", "Tumblr Channel", "Flickr Channel", "Vimeo Channel"};
        for (int i = 0; i < 10; i++) {
            SocialChannel channel = new SocialChannel();
            channel.setChannelName(channelNames[i]);
            channel.setLocation("Global");
            channel.setCreatedAt(LocalDateTime.now());
            channel.setUpdatedAt(LocalDateTime.now());
            channel.setChannelPrices(prices); // Assuming all channels get the same prices
            channels.add(channel);
        }
        return channels;
    }

    private static void saveData(List<ChannelPrice> prices, List<SocialChannel> channels) {
        prices.forEach(DB::save);
        channels.forEach(DB::save);
    }

}
