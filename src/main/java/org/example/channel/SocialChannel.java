package org.example.channel;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "social_channel")
@Data
public class SocialChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private int channelId;
    @Column(name = "channel_name")
    private String channelName;
    @Column(name = "location")
    private String location;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @ManyToMany
    @JoinTable(
            name = "channel_prices_mapping",
            joinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id", referencedColumnName = "price_id")
    )
    private List<ChannelPrice> channelPrices;
}
