package org.example;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "channel_price")
@Data
public class ChannelPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private int priceId;
    @Column(name = "price_name")
    private String priceName;
    @Column(name = "price_value")
    private int priceValue;
    @Column(name = "unit")
    private String unit;
    @ManyToMany(mappedBy = "channelPrices") // Định nghĩa quan hệ ManyToMany từ SocialChannel
    private List<SocialChannel> socialChannels;
}
