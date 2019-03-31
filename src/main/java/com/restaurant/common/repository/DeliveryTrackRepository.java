package com.restaurant.common.repository;

import com.restaurant.common.model.DeliveryTrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTrackRepository extends JpaRepository<DeliveryTrack, Integer> {
}
